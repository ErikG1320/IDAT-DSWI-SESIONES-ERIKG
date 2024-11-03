package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.Ventas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO {

    // Método para agregar una venta usando el procedimiento almacenado `agregar_venta`
    public void agregarVenta(Ventas venta) {
        String sql = "{CALL agregar_venta(?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, venta.getId_cliente());
            stmt.setInt(2, venta.getId_empleado());
            // Convertir LocalDate a java.sql.Date
            stmt.setDate(3, Date.valueOf(venta.getFecha_venta()));
            stmt.setDouble(4, venta.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una venta por su ID
    public Ventas obtenerVenta(int idVenta) {
        String sql = "SELECT * FROM Ventas WHERE id_venta = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ventas(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado"),
                        rs.getDate("fecha_venta").toLocalDate(), // Convertir java.sql.Date a LocalDate
                        rs.getDouble("total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todas las ventas usando el procedimiento almacenado `obtener_ventas`
    public List<Ventas> obtenerVentas() {
        List<Ventas> ventas = new ArrayList<>();
        String sql = "{CALL obtener_ventas()}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ventas venta = new Ventas(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado"),
                        rs.getDate("fecha_venta").toLocalDate(), // Convertir java.sql.Date a LocalDate
                        rs.getDouble("total")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // Método para actualizar una venta usando el procedimiento almacenado `actualizar_venta`
    public boolean actualizarVenta(Ventas venta) {
        String sql = "{CALL actualizar_venta(?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, venta.getId_venta());
            stmt.setInt(2, venta.getId_cliente());
            stmt.setInt(3, venta.getId_empleado());
            // Convertir LocalDate a java.sql.Date
            stmt.setDate(4, Date.valueOf(venta.getFecha_venta()));
            stmt.setDouble(5, venta.getTotal());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar una venta usando el procedimiento almacenado `eliminar_venta`
    public boolean eliminarVenta(int idVenta) {
        String sql = "{CALL eliminar_venta(?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idVenta);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
