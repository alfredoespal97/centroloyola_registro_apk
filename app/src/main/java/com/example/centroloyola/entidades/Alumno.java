package com.example.centroloyola.entidades;

public class Alumno {

    private int id;
    private String nombre;
    private int edad;
    private String escuela;
    private String numero_padre;
    private String numero_madre;
    private String numero_otro;
    private String direccion;
    private String nota;
    private int id_registro;


    public Alumno(int id, String nombre, int edad, String escuela, String numero_padre, String numero_madre, String numero_otro, String direccion, String nota, int id_registro) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.escuela = escuela;
        this.numero_padre = numero_padre;
        this.numero_madre = numero_madre;
        this.numero_otro = numero_otro;
        this.direccion = direccion;
        this.nota = nota;
        this.id_registro = id_registro;
    }

    public Alumno() {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getNumero_padre() {
        return numero_padre;
    }

    public void setNumero_padre(String numero_padre) {
        this.numero_padre = numero_padre;
    }

    public String getNumero_madre() {
        return numero_madre;
    }

    public void setNumero_madre(String numero_madre) {
        this.numero_madre = numero_madre;
    }

    public String getNumero_otro() {
        return numero_otro;
    }

    public void setNumero_otro(String numero_otro) {
        this.numero_otro = numero_otro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }
}
