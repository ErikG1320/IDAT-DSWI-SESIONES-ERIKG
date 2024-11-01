package com.tienda.model;

import java.util.Date;

public class Ventas {
    private int id_venta;
    private int id_cliente;
    private int id_empleado;
    private Date fecha_venta;
    private double total;

    // Constructor

    public Ventas(int id_venta, int id_cliente, int id_empleado, Date fecha_venta, double total) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_venta = fecha_venta;
        this.total = total;
    }
    // Constructor sin id

    public Ventas(int id_cliente, int id_empleado, Date fecha_venta, double total) {
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_venta = fecha_venta;
        this.total = total;
    }

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

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
