package com.example.centroloyola.entidades;

public class Calendario {

    private int id;
    private String fecha;
    private int id_registro;
    private int id_alumno;

    public Calendario(int id, String fecha, int id_registro, int id_alumno) {
        this.id = id;
        this.fecha = fecha;
        this.id_registro = id_registro;
        this.id_alumno = id_alumno;
    }

    public Calendario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }
}
