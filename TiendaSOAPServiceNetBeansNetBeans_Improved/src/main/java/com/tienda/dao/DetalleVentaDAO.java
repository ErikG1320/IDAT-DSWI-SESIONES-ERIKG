package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {
    // Método para agregar un detalle de venta usando el procedimiento almacenado `agregar_detalle_venta`
    public void agregarDetalleVenta(DetalleVenta detalleVenta) {
        String sql = "{CALL agregar_detalle_venta(?, ?, ?, ?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, detalleVenta.getId_venta());           // Sin cambios
            callableStatement.setInt(2, detalleVenta.getId_producto());        // Sin cambios
            callableStatement.setInt(3, detalleVenta.getCantidad());           // Sin cambios
            callableStatement.setDouble(4, detalleVenta.getPrecio_unitario()); // Sin cambios
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un detalle de venta por ID
    public DetalleVenta obtenerDetalleVentaPorId(int idDetalle) {
        DetalleVenta detalleVenta = null;
        String sql = "SELECT * FROM Detalle_Venta WHERE id_detalle = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDetalle);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    detalleVenta = new DetalleVenta(
                            resultSet.getInt("id_detalle"),
                            resultSet.getInt("id_venta"),
                            resultSet.getInt("id_producto"),
                            resultSet.getInt("cantidad"),
                            resultSet.getDouble("precio_unitario")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVenta;
    }

    // Método para obtener todos los detalles de venta usando el procedimiento almacenado `obtener_detalles_venta`
    public List<DetalleVenta> obtenerTodosDetallesVenta() {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "{CALL obtener_detalles_venta()}"; // Llamar a un procedimiento almacenado
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql);
             ResultSet resultSet = callableStatement.executeQuery()) {

            while (resultSet.next()) {
                DetalleVenta detalleVenta = new DetalleVenta(
                        resultSet.getInt("id_detalle"),
                        resultSet.getInt("id_venta"),
                        resultSet.getInt("id_producto"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_unitario")
                );
                detalles.add(detalleVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    // Método para obtener detalles de venta por ID de venta
    public List<DetalleVenta> obtenerDetallesPorVenta(int ventaId) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM Detalle_Venta WHERE id_venta = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ventaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    DetalleVenta detalleVenta = new DetalleVenta(
                            resultSet.getInt("id_detalle"),
                            resultSet.getInt("id_venta"),
                            resultSet.getInt("id_producto"),
                            resultSet.getInt("cantidad"),
                            resultSet.getDouble("precio_unitario")
                    );
                    detalles.add(detalleVenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    // Método para eliminar un detalle de venta usando el procedimiento almacenado `eliminar_detalle_venta`
    public boolean eliminarDetalleVenta(int idDetalle) {
        boolean eliminado = false;
        String sql = "{CALL eliminar_detalle_venta(?)}";
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, idDetalle);
            eliminado = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
    }
}
