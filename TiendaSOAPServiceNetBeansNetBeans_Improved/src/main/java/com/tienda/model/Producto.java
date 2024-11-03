package com.tienda.model;

public class Producto {
    private int id_producto;                // ID del producto
    private String nombre_producto;         // Nombre del producto
    private String descripcion_producto;    // Descripción del producto
    private double precio;                  // Precio del producto
    private int stock;                      // Cantidad disponible en stock
    private String fecha_agregado;          // Fecha en que se agregó el producto

    // Constructor con ID (para casos de actualización)
    public Producto(int id_producto, String nombre_producto, String descripcion_producto, double precio, int stock, String fecha_agregado) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.stock = stock;
        this.fecha_agregado = fecha_agregado;
    }

    // Constructor sin ID (para agregar nuevos productos)
    public Producto(String nombre_producto, String descripcion_producto, double precio, int stock, String fecha_agregado) {
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.stock = stock;
        this.fecha_agregado = fecha_agregado;
    }

    // Getters y Setters
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFecha_agregado() {
        return fecha_agregado;
    }

    public void setFecha_agregado(String fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }
}
