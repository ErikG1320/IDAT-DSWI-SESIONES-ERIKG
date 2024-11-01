package com.tienda.dao;

import com.tienda.model.DetalleVenta;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "DetalleVentaDAO")
public class DetalleVentaDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:5000/Tienda_Micas";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "109Inuyash@";

    // Método para agregar un detalle de venta
    @WebMethod(operationName = "agregarDetalleVenta")
    public String agregarDetalleVenta(
            @WebParam(name = "ventaId") int ventaId,
            @WebParam(name = "productoId") int productoId,
            @WebParam(name = "cantidad") int cantidad,
            @WebParam(name = "precioUnitario") double precioUnitario) {
        String response = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{call agregar_detalle_venta(?, ?, ?, ?)}");
            callableStatement.setInt(1, ventaId);
            callableStatement.setInt(2, productoId);
            callableStatement.setInt(3, cantidad);
            callableStatement.setDouble(4, precioUnitario);
            callableStatement.execute();
            response = "Detalle de venta agregado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al agregar detalle de venta: " + e.getMessage();
        }
        return response;
    }

    // Método para obtener un detalle de venta por ID
    @WebMethod(operationName = "obtenerDetalleVentaPorId")
    public DetalleVenta obtenerDetalleVentaPorId(@WebParam(name = "idDetalle") int idDetalle) {
        DetalleVenta detalleVenta = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Detalle_Venta WHERE id_detalle = ?");
            preparedStatement.setInt(1, idDetalle);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                detalleVenta = new DetalleVenta(
                        resultSet.getInt("id_detalle"),
                        resultSet.getInt("id_venta"),
                        resultSet.getInt("id_producto"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_unitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVenta;
    }

    // Método para obtener todos los detalles de venta
    @WebMethod(operationName = "obtenerTodosDetallesVenta")
    public List<DetalleVenta> obtenerTodosDetallesVenta() {
        List<DetalleVenta> detalles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Detalle_Venta");
            ResultSet resultSet = preparedStatement.executeQuery();

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
    @WebMethod(operationName = "obtenerDetallesPorVenta")
    public List<DetalleVenta> obtenerDetallesPorVenta(@WebParam(name = "ventaId") int ventaId) {
        List<DetalleVenta> detalles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Detalle_Venta WHERE id_venta = ?");
            preparedStatement.setInt(1, ventaId);
            ResultSet resultSet = preparedStatement.executeQuery();

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
}
