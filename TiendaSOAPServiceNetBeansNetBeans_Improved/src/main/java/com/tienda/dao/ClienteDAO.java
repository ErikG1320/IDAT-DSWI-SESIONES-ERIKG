package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para agregar un cliente usando el procedimiento almacenado `agregar_cliente`
    public void agregarCliente(Cliente cliente) {
        String sql = "{CALL agregar_cliente(?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
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

    // Método para obtener todos los clientes usando el procedimiento almacenado `obtener_clientes`
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "{CALL obtener_clientes()}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
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

    // Método para actualizar un cliente usando el procedimiento almacenado `actualizar_cliente`
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "{CALL actualizar_cliente(?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, cliente.getId_cliente());
            stmt.setString(2, cliente.getNombre_cliente());
            stmt.setString(3, cliente.getEmail_cliente());
            stmt.setString(4, cliente.getTelefono_cliente());
            stmt.setString(5, cliente.getDireccion_cliente());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un cliente usando el procedimiento almacenado `eliminar_cliente`
    public boolean eliminarCliente(int idCliente) {
        String sql = "{CALL eliminar_cliente(?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
