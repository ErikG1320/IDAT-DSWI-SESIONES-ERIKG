package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para agregar un cliente
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nombre_cliente, email_cliente, telefono_cliente, direccion_cliente, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre_cliente());
            stmt.setString(2, cliente.getEmail_cliente());
            stmt.setString(3, cliente.getTelefono_cliente());
            stmt.setString(4, cliente.getDireccion_cliente());
            stmt.setDate(5, new java.sql.Date(cliente.getFecha_registro().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un cliente por su ID
    public Cliente obtenerCliente(int idCliente) {
        String sql = "SELECT * FROM Clientes WHERE id_cliente = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("email_cliente"),
                        rs.getString("telefono_cliente"),
                        rs.getString("direccion_cliente"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("email_cliente"),
                        rs.getString("telefono_cliente"),
                        rs.getString("direccion_cliente"),
                        rs.getDate("fecha_registro")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Clientes SET nombre_cliente = ?, email_cliente = ?, telefono_cliente = ?, direccion_cliente = ?, fecha_registro = ? WHERE id_cliente = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre_cliente());
            stmt.setString(2, cliente.getEmail_cliente());
            stmt.setString(3, cliente.getTelefono_cliente());
            stmt.setString(4, cliente.getDireccion_cliente());
            stmt.setDate(5, new java.sql.Date(cliente.getFecha_registro().getTime()));
            stmt.setInt(6, cliente.getId_cliente());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un cliente
    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
