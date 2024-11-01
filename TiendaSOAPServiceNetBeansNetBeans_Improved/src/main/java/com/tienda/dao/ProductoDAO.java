package com.tienda.dao;

import com.tienda.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    // Constructor que inicializa la conexión a la base de datos
    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar un nuevo producto
    public void agregarProducto(Producto producto) throws SQLException {
        String sql = "CALL agregar_producto(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre_producto());
            statement.setString(2, producto.getDescripcion_producto());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setString(5, producto.getFecha_agregado());
            statement.executeUpdate();
        }
    }

    // Método para obtener un producto por ID
    public Producto obtenerProductoPorId(int id_producto) throws SQLException {
        Producto producto = null;
        String sql = "SELECT * FROM Productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_producto);
            ResultSet resultSet = statement.executeQuery();
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
        return producto;
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
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
        }
        return productos;
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE Productos SET nombre_producto = ?, descripcion_producto = ?, precio = ?, stock = ?, fecha_agregado = ? WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre_producto());
            statement.setString(2, producto.getDescripcion_producto());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setString(5, producto.getFecha_agregado());
            statement.setInt(6, producto.getId_producto());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un producto por ID
    public void eliminarProducto(int id_producto) throws SQLException {
        String sql = "DELETE FROM Productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_producto);
            statement.executeUpdate();
        }
    }
}
