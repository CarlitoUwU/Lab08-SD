package com.sd.lab8sd.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Proyecto {
    private int idProy;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor
    public Proyecto(int idProy, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idProy = idProy;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public int getIdProy() {
        return idProy;
    }

    public void setIdProy(int idProy) {
        this.idProy = idProy;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return String.format("Proyecto{idProy=%d, nombre='%s', fechaInicio=%s, fechaFin=%s}",
                idProy, nombre, fechaInicio, fechaFin);
    }
}
