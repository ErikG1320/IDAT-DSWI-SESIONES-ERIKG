package com.tienda.dao;

import com.tienda.model.Ventas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO {
    private Connection connection;

    public boolean agregarVenta(Ventas venta) {
        String query = "INSERT INTO Ventas (id_cliente, id_empleado, fecha_venta, total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, venta.getId_cliente());
            statement.setInt(2, venta.getId_empleado());
            statement.setDate(3, new java.sql.Date(venta.getFecha_venta().getTime()));
            statement.setDouble(4, venta.getTotal());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ventas obtenerVenta(int id_venta) {
        String query = "SELECT * FROM Ventas WHERE id_venta = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_venta);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Ventas(
                        resultSet.getInt("id_venta"),
                        resultSet.getInt("id_cliente"),
                        resultSet.getInt("id_empleado"),
                        resultSet.getDate("fecha_venta"),
                        resultSet.getDouble("total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ventas> obtenerVentas() {
        List<Ventas> ventas = new ArrayList<>();
        String query = "SELECT * FROM Ventas";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ventas venta = new Ventas(
                        resultSet.getInt("id_venta"),
                        resultSet.getInt("id_cliente"),
                        resultSet.getInt("id_empleado"),
                        resultSet.getDate("fecha_venta"),
                        resultSet.getDouble("total")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public boolean actualizarVenta(Ventas venta) {
        String query = "UPDATE Ventas SET id_cliente = ?, id_empleado = ?, fecha_venta = ?, total = ? WHERE id_venta = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, venta.getId_cliente());
            statement.setInt(2, venta.getId_empleado());
            statement.setDate(3, new java.sql.Date(venta.getFecha_venta().getTime()));
            statement.setDouble(4, venta.getTotal());
            statement.setInt(5, venta.getId_venta());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVenta(int id_venta) {
        String query = "DELETE FROM Ventas WHERE id_venta = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_venta);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
