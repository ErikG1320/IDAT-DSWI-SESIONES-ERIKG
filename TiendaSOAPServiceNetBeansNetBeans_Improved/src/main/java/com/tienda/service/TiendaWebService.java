package com.tienda.service;

import com.tienda.dao.ClienteDAO;
import com.tienda.dao.EmpleadoDAO;
import com.tienda.dao.VentasDAO;
import com.tienda.dao.DetalleVentaDAO;
import com.tienda.model.Cliente;
import com.tienda.model.Empleado;
import com.tienda.model.Ventas;
import com.tienda.model.DetalleVenta;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Date;
import java.util.List;

@WebService
public class TiendaWebService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private VentasDAO ventasDAO = new VentasDAO();
    private DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();

    // Métodos para Clientes
    @WebMethod
    public String agregarCliente(String nombre, String email, String telefono, String direccion, String fechaRegistro) {
        try {
            Cliente cliente = new Cliente(nombre, email, telefono, direccion, Date.valueOf(fechaRegistro));
            clienteDAO.agregarCliente(cliente);
            return "Cliente agregado exitosamente";
        } catch (Exception e) {
            return "Error al agregar cliente: " + e.getMessage();
        }
    }

    // Métodos para Empleados
    @WebMethod
    public String agregarEmpleado(String nombre, String email, String telefono, String puesto, String fechaContratacion) {
        try {
            Empleado empleado = new Empleado(nombre, email, telefono, puesto, fechaContratacion);
            empleadoDAO.agregarEmpleado(empleado);
            return "Empleado agregado exitosamente";
        } catch (Exception e) {
            return "Error al agregar empleado: " + e.getMessage();
        }
    }

    // Métodos para Ventas
    @WebMethod
    public String realizarVenta(int idCliente, int idEmpleado, String fechaVenta, double total) {
        try {
            Ventas venta = new Ventas(idCliente, idEmpleado, Date.valueOf(fechaVenta), total);
            ventasDAO.agregarVenta(venta);
            return "Venta realizada exitosamente";
        } catch (Exception e) {
            return "Error al realizar venta: " + e.getMessage();
        }
    }

    @WebMethod
    public List<Ventas> obtenerTodasLasVentas() {
        try {
            return ventasDAO.obtenerVentas();
        } catch (Exception e) {
            return null; // Considera registrar el error aquí
        }
    }

    @WebMethod
    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        try {
            return detalleVentaDAO.obtenerDetallesPorVenta(idVenta); // Método actualizado
        } catch (Exception e) {
            return null; // Considera registrar el error aquí
        }
    }

    // Métodos para actualizar y eliminar clientes y empleados
    @WebMethod
    public String actualizarCliente(int idCliente, String nombre, String email, String telefono, String direccion, String fechaRegistro) {
        try {
            Cliente cliente = new Cliente(idCliente, nombre, email, telefono, direccion, Date.valueOf(fechaRegistro));
            clienteDAO.actualizarCliente(cliente);
            return "Cliente actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar cliente: " + e.getMessage();
        }
    }

    @WebMethod
    public String actualizarEmpleado(int idEmpleado, String nombre, String email, String telefono, String puesto, String fechaContratacion) {
        try {
            Empleado empleado = new Empleado(idEmpleado, nombre, email, telefono, puesto, fechaContratacion);
            empleadoDAO.actualizarEmpleado(empleado);
            return "Empleado actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar empleado: " + e.getMessage();
        }
    }

    @WebMethod
    public String eliminarCliente(int idCliente) {
        try {
            clienteDAO.eliminarCliente(idCliente);
            return "Cliente eliminado exitosamente";
        } catch (Exception e) {
            return "Error al eliminar cliente: " + e.getMessage();
        }
    }

    @WebMethod
    public String eliminarEmpleado(int idEmpleado) {
        try {
            empleadoDAO.eliminarEmpleado(idEmpleado);
            return "Empleado eliminado exitosamente";
        } catch (Exception e) {
            return "Error al eliminar empleado: " + e.getMessage();
        }
    }

    @WebMethod
    public List<Cliente> obtenerCliente() {
        try {
            return clienteDAO.obtenerClientes();
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public List<Empleado> obtenerTodosEmpleados() {
        try {
            return empleadoDAO.obtenerTodosEmpleados();
        } catch (Exception e) {
            return null;
        }
    }
}
