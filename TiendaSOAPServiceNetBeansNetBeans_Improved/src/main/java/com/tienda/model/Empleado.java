package com.tienda.model;

import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;                // ID del empleado
    private String nombreEmpleado;         // Nombre del empleado
    private String emailEmpleado;          // Correo electrónico del empleado
    private String telefonoEmpleado;       // Teléfono del empleado
    private String puesto;                 // Puesto del empleado
    private LocalDate fechaContratacion;  // Fecha de contratación del empleado

    // Constructor con ID (para casos de actualización)
    public Empleado(int idEmpleado, String nombreEmpleado, String emailEmpleado, String telefonoEmpleado, String puesto, LocalDate fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
    }
    
    // Constructor sin ID (para agregar nuevos empleados)
    public Empleado(String nombreEmpleado, String emailEmpleado, String telefonoEmpleado, String puesto, LocalDate fechaContratacion) {
        this.nombreEmpleado = nombreEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
    }
    
    // Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
