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

    @Lob
    @Column(name = "imagen", columnDefinition = "LONGTEXT")
    private String imagen;

    @Column(name = "id_cliente")
    private Integer idCliente;

    // pendiente_cotizacion -> cotizado -> aceptado
    private String estado;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}