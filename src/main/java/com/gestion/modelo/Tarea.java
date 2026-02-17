package com.gestion.modelo;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private int proyectoId;
    private int estadoId;

    // variables para mostrar informacion en el json
    private String nombreProyecto;
    private String nombreEstado;

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, int proyectoId, int estadoId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proyectoId = proyectoId;
        this.estadoId = estadoId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getProyectoId() { return proyectoId; }
    public void setProyectoId(int proyectoId) { this.proyectoId = proyectoId; }
    public int getEstadoId() { return estadoId; }
    public void setEstadoId(int estadoId) { this.estadoId = estadoId; }

    public String getNombreProyecto() { return nombreProyecto; }
    public void setNombreProyecto(String nombreProyecto) { this.nombreProyecto = nombreProyecto; }
    public String getNombreEstado() { return nombreEstado; }
    public void setNombreEstado(String nombreEstado) { this.nombreEstado = nombreEstado; }
}
