package com.tienda.model;

public class Empleado {
    private int id_empleado;
    private String nombre_empleado;
    private String email_empleado;
    private String telefono_empleado;
    private String puesto;
    private String fecha_contratacion;

    // Constructor
    public Empleado(int id_empleado, String nombre_empleado, String email_empleado, String telefono_empleado, String puesto, String fecha_contratacion) {
        this.id_empleado = id_empleado;
        this.nombre_empleado = nombre_empleado;
        this.email_empleado = email_empleado;
        this.telefono_empleado = telefono_empleado;
        this.puesto = puesto;
        this.fecha_contratacion = fecha_contratacion;
    }
    
    // Constructor sin ID (para agregar nuevos empleados)
    public Empleado(String nombre_empleado, String email_empleado, String telefono_empleado, String puesto, String fecha_contratacion) {
        this.nombre_empleado = nombre_empleado;
        this.email_empleado = email_empleado;
        this.telefono_empleado = telefono_empleado;
        this.puesto = puesto;
        this.fecha_contratacion = fecha_contratacion;
    }
    
    // Getters y Setters
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getEmail_empleado() {
        return email_empleado;
    }

    public void setEmail_empleado(String email_empleado) {
        this.email_empleado = email_empleado;
    }

    public String getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getPuesto() {  // Agregado: getter para puesto
        return puesto;
    }

    public void setPuesto(String puesto) {  // Agregado: setter para puesto
        this.puesto = puesto;
    }

    public String getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(String fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
}
