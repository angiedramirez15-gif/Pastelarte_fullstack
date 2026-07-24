package com.pastelarte.pastelarte_api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_cliente")
    private Integer idCliente;

    private LocalDate fecha;

    private String estado;

    private Double total;

    @Column(name = "id_pago")
    private Integer idPago;

    @Lob
    @Column(name = "comprobante", columnDefinition = "LONGTEXT")
    private String comprobante;

    @Column(name = "numero_nequi")
    private String numeroNequi;

    public Pedido() {}

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;}

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getNumeroNequi() {
        return numeroNequi;
    }

    public void setNumeroNequi(String numeroNequi) {
        this.numeroNequi = numeroNequi;
    }
}