package com.sd.lab8sd.model;

import java.time.LocalDateTime;

public class Departamento {
    private int id;
    private String nombre;
    private String telefono;
    private String fax;

    public Departamento(int id, String nombre, String telefono, String fax) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fax = fax;
    }

    // Getters y Setters
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return String.format("Departamento{id=%d, nombre='%s', telefono='%s', fax='%s'}",
                id, nombre, telefono, fax);
    }
}
