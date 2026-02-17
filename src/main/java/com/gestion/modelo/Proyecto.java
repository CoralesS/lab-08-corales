package com.gestion.modelo;

import java.sql.Date;

public class Proyecto {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;

    public Proyecto() {}

    public Proyecto(int id, String nombre, String descripcion, Date fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
}
