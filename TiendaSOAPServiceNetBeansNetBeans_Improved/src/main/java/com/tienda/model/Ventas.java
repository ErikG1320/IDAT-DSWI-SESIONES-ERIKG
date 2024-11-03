package com.tienda.model;

import java.time.LocalDate;

public class Ventas {
    private int id_venta;        // ID de la venta
    private int id_cliente;      // ID del cliente asociado a la venta
    private int id_empleado;     // ID del empleado que realizó la venta
    private LocalDate fecha_venta; // Fecha en que se realizó la venta
    private double total;        // Total de la venta

    // Constructor con ID (para casos de actualización)
    public Ventas(int id_venta, int id_cliente, int id_empleado, LocalDate fecha_venta, double total) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_venta = fecha_venta;
        this.total = total;
    }

    // Constructor sin ID (para agregar nuevas ventas)
    public Ventas(int id_cliente, int id_empleado, LocalDate fecha_venta, double total) {
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_venta = fecha_venta;
        this.total = total;
    }

    // Getters y Setters
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
