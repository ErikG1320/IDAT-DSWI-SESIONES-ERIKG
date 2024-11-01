package com.tienda.dao;

import com.tienda.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:5000/Tienda_Micas";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "109Inuyash@";

    // Método para agregar un nuevo empleado
    public String agregarEmpleado(Empleado empleado) {
        String response = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{call agregar_empleado(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, empleado.getNombre_empleado());
            callableStatement.setString(2, empleado.getEmail_empleado());
            callableStatement.setString(3, empleado.getTelefono_empleado());
            callableStatement.setString(4, empleado.getPuesto());
            callableStatement.setString(5, empleado.getFecha_contratacion());
            callableStatement.execute();
            response = "Empleado agregado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al agregar empleado: " + e.getMessage();
        }
        return response;
    }

    // Método para actualizar un empleado existente
    public String actualizarEmpleado(Empleado empleado) {
        String response = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{call actualizar_empleado(?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, empleado.getId_empleado());
            callableStatement.setString(2, empleado.getNombre_empleado());
            callableStatement.setString(3, empleado.getEmail_empleado());
            callableStatement.setString(4, empleado.getTelefono_empleado());
            callableStatement.setString(5, empleado.getPuesto());
            callableStatement.execute();
            response = "Empleado actualizado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al actualizar empleado: " + e.getMessage();
        }
        return response;
    }

    // Método para eliminar un empleado por ID
    public String eliminarEmpleado(int id_empleado) {
        String response = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{call eliminar_empleado(?)}");
            callableStatement.setInt(1, id_empleado);
            callableStatement.execute();
            response = "Empleado eliminado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al eliminar empleado: " + e.getMessage();
        }
        return response;
    }

    // Método para obtener un empleado por ID
    public Empleado obtenerEmpleadoPorId(int id_empleado) {
        Empleado empleado = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Empleados WHERE id_empleado = ?");
            preparedStatement.setInt(1, id_empleado);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                empleado = new Empleado(
                    resultSet.getInt("id_empleado"),
                    resultSet.getString("nombre_empleado"),
                    resultSet.getString("email_empleado"),
                    resultSet.getString("telefono_empleado"),
                    resultSet.getString("puesto"),
                    resultSet.getString("fecha_contratacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    // Método para obtener todos los empleados
    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Empleados");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                    resultSet.getInt("id_empleado"),
                    resultSet.getString("nombre_empleado"),
                    resultSet.getString("email_empleado"),
                    resultSet.getString("telefono_empleado"),
                    resultSet.getString("puesto"),
                    resultSet.getString("fecha_contratacion")
                );
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
