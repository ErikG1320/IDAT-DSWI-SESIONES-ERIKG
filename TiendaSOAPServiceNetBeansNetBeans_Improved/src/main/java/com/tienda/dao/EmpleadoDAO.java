package com.tienda.dao;

import com.tienda.config.DBConnection;
import com.tienda.model.Empleado;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Método para agregar un nuevo empleado
    public String agregarEmpleado(Empleado empleado) {
        String response;
        String sql = "{call agregar_empleado(?, ?, ?, ?, ?)}"; // Asegúrate de que el procedimiento almacenado esté correcto.
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, empleado.getNombreEmpleado());
            callableStatement.setString(2, empleado.getEmailEmpleado());
            callableStatement.setString(3, empleado.getTelefonoEmpleado());
            callableStatement.setString(4, empleado.getPuesto());
            callableStatement.setString(5, empleado.getFechaContratacion().format(DATE_FORMATTER)); // Formateo de LocalDate
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
        String response;
        String sql = "{call actualizar_empleado(?, ?, ?, ?, ?)}"; // Asegúrate de que el procedimiento almacenado esté correcto.
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, empleado.getIdEmpleado());
            callableStatement.setString(2, empleado.getNombreEmpleado());
            callableStatement.setString(3, empleado.getEmailEmpleado());
            callableStatement.setString(4, empleado.getTelefonoEmpleado());
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
    public String eliminarEmpleado(int idEmpleado) {
        String response;
        String sql = "{call eliminar_empleado(?)}"; // Asegúrate de que el procedimiento almacenado esté correcto.
        try (Connection connection = DBConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, idEmpleado);
            callableStatement.execute();
            response = "Empleado eliminado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Error al eliminar empleado: " + e.getMessage();
        }
        return response;
    }

    // Método para obtener un empleado por ID
    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        Empleado empleado = null;
        String sql = "SELECT * FROM Empleados WHERE id_empleado = ?"; // Verifica que la tabla y la columna existan.
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idEmpleado);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new Empleado(
                        resultSet.getInt("id_empleado"),
                        resultSet.getString("nombre_empleado"),
                        resultSet.getString("email_empleado"),
                        resultSet.getString("telefono_empleado"),
                        resultSet.getString("puesto"),
                        LocalDate.parse(resultSet.getString("fecha_contratacion"), DATE_FORMATTER) // Parseo de LocalDate
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    // Método para obtener todos los empleados
    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados"; // Verifica que la tabla exista.
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                    resultSet.getInt("id_empleado"),
                    resultSet.getString("nombre_empleado"),
                    resultSet.getString("email_empleado"),
                    resultSet.getString("telefono_empleado"),
                    resultSet.getString("puesto"),
                    LocalDate.parse(resultSet.getString("fecha_contratacion"), DATE_FORMATTER) // Parseo de LocalDate
                );
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
