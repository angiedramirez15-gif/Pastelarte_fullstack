// Personalizacion.java
package com.pastelarte.pastelarte_api.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "personalizacion")
public class Personalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personalizacion")
    private Integer idPersonalizacion;

    private String tamano;

    private String sabor;

    private String decoraciones;

    private String descripcion;

    @Column(name = "costo_extra", precision = 10, scale = 2)
    private BigDecimal costoExtra;

    public Personalizacion() {}

    public Integer getIdPersonalizacion() {
        return idPersonalizacion;
    }

    public void setIdPersonalizacion(Integer idPersonalizacion) {
        this.idPersonalizacion = idPersonalizacion;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
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

    public BigDecimal getCostoExtra() {
        return costoExtra;
    }

    public void setCostoExtra(BigDecimal costoExtra) {
        this.costoExtra = costoExtra;
    }
}