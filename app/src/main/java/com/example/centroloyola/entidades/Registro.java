package com.example.centroloyola.entidades;

public class Registro {

    private int id;
    private String nombre;
    private String curso;
    private String grado;

    public Registro(int id, String nombre, String curso, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.grado = grado;
    }

    public Registro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
