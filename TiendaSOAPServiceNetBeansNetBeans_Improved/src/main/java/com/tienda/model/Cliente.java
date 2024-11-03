package com.tienda.model;

import java.util.Date;

public class Cliente {
    private int id_cliente;               // ID del cliente
    private String nombre_cliente;        // Nombre del cliente
    private String email_cliente;         // Email del cliente
    private String telefono_cliente;      // Teléfono del cliente
    private String direccion_cliente;     // Dirección del cliente
    private Date fecha_registro;          // Fecha de registro del cliente

    // Constructor con ID (para casos de actualización)
    public Cliente(int id_cliente, String nombre_cliente, String email_cliente, String telefono_cliente, String direccion_cliente, Date fecha_registro) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.email_cliente = email_cliente;
        this.telefono_cliente = telefono_cliente;
        this.direccion_cliente = direccion_cliente;
        this.fecha_registro = fecha_registro;
    }

    // Constructor sin ID (para agregar nuevos clientes)
    public Cliente(String nombre_cliente, String email_cliente, String telefono_cliente, String direccion_cliente, Date fecha_registro) {
        this.nombre_cliente = nombre_cliente;
        this.email_cliente = email_cliente;
        this.telefono_cliente = telefono_cliente;
        this.direccion_cliente = direccion_cliente;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
