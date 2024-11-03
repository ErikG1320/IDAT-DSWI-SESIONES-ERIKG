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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechaRegistro);
            Cliente cliente = new Cliente(nombre, email, telefono, direccion, fecha);
            clienteDAO.agregarCliente(cliente);
            return "Cliente agregado exitosamente";
        } catch (Exception e) {
            return "Error al agregar cliente: " + e.getMessage();
        }
    }

    @WebMethod
    public String actualizarCliente(int idCliente, String nombre, String email, String telefono, String direccion, String fechaRegistro) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechaRegistro);
            Cliente cliente = new Cliente(idCliente, nombre, email, telefono, direccion, fecha);
            clienteDAO.actualizarCliente(cliente);
            return "Cliente actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar cliente: " + e.getMessage();
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
    public List<Cliente> obtenerClientes() {
        try {
            return clienteDAO.listarClientes();
        } catch (Exception e) {
            return null; // Considera registrar el error aquí
        }
    }

    // Métodos para Empleados
    @WebMethod
    public String agregarEmpleado(String nombre, String email, String telefono, String puesto, String fechaContratacion) {
        try {
            LocalDate fecha = LocalDate.parse(fechaContratacion);
            Empleado empleado = new Empleado(nombre, email, telefono, puesto, fecha);
            empleadoDAO.agregarEmpleado(empleado);
            return "Empleado agregado exitosamente";
        } catch (Exception e) {
            return "Error al agregar empleado: " + e.getMessage();
        }
    }

    @WebMethod
    public String actualizarEmpleado(int idEmpleado, String nombre, String email, String telefono, String puesto, String fechaContratacion) {
        try {
            LocalDate fecha = LocalDate.parse(fechaContratacion);
            Empleado empleado = new Empleado(idEmpleado, nombre, email, telefono, puesto, fecha);
            empleadoDAO.actualizarEmpleado(empleado);
            return "Empleado actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar empleado: " + e.getMessage();
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
    public List<Empleado> obtenerTodosEmpleados() {
        try {
            return empleadoDAO.obtenerTodosEmpleados();
        } catch (Exception e) {
            return null;
        }
    }

    // Métodos para Ventas
    @WebMethod
    public String realizarVenta(int idCliente, int idEmpleado, String fechaVenta, double total) {
        try {
            LocalDate fecha = LocalDate.parse(fechaVenta);
            Ventas venta = new Ventas(idCliente, idEmpleado, fecha, total);
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
            return null;
        }
    }

    @WebMethod
    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        try {
            return detalleVentaDAO.obtenerDetallesPorVenta(idVenta);
        } catch (Exception e) {
            return null;
        }
    }
}
