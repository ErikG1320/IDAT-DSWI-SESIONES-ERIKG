package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    // Método para agregar un producto usando el procedimiento almacenado `agregar_producto`
    public void agregarProducto(Producto producto) {
        String sql = "{CALL agregar_producto(?, ?, ?, ?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, producto.getNombre_producto()); 
            callableStatement.setDouble(2, producto.getPrecio());         
            callableStatement.setInt(3, producto.getStock());              
            callableStatement.setString(4, producto.getDescripcion_producto());
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un producto por ID
    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM Producto WHERE id_producto = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idProducto);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new Producto(
                            resultSet.getInt("id_producto"),
                            resultSet.getString("nombre_producto"), 
                            resultSet.getString("descripcion_producto"), 
                            resultSet.getDouble("precio"),
                            resultSet.getInt("stock"),
                            resultSet.getString("fecha_agregado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Método para obtener todos los productos usando el procedimiento almacenado `obtener_productos`
    public List<Producto> obtenerTodosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "{CALL obtener_productos()}"; // Llamar a un procedimiento almacenado
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql);
             ResultSet resultSet = callableStatement.executeQuery()) {

            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getInt("id_producto"),
                        resultSet.getString("nombre_producto"),
                        resultSet.getString("descripcion_producto"),
                        resultSet.getDouble("precio"),
                        resultSet.getInt("stock"),
                        resultSet.getString("fecha_agregado")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para eliminar un producto usando el procedimiento almacenado `eliminar_producto`
    public boolean eliminarProducto(int idProducto) {
        boolean eliminado = false;
        String sql = "{CALL eliminar_producto(?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, idProducto);
            eliminado = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    // Método para actualizar un producto usando el procedimiento almacenado `actualizar_producto`
    public boolean actualizarProducto(Producto producto) {
        boolean actualizado = false;
        String sql = "{CALL actualizar_producto(?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, producto.getId_producto());
            callableStatement.setString(2, producto.getNombre_producto());
            callableStatement.setDouble(3, producto.getPrecio());          
            callableStatement.setInt(4, producto.getStock());              
            callableStatement.setString(5, producto.getDescripcion_producto());
            actualizado = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }
}
