package com.pastelarte.pastelarte_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "personalizacion")
public class Personalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personalizacion")
    private Integer idPersonalizacion;

    private String tamaño;

    private String sabor;

    private String decoraciones;

    private String descripcion;

    @Column(name = "costo_extra")
    private Float costoExtra;

    public Personalizacion() {}

    public Integer getIdPersonalizacion() {
        return idPersonalizacion;
    }

    public void setIdPersonalizacion(Integer idPersonalizacion) {
        this.idPersonalizacion = idPersonalizacion;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getDecoraciones() {
        return decoraciones;
    }

    public void setDecoraciones(String decoraciones) {
        this.decoraciones = decoraciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCostoExtra() {
        return costoExtra;
    }

    public void setCostoExtra(Float costoExtra) {
        this.costoExtra = costoExtra;
    }
}