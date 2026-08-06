package com.pastelarte.pastelarte_api.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    private LocalDate fecha;

    private String estado;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "id_pago")
    private Integer idPago;

    // AHORA GUARDA LA RUTA/NOMBRE DE LA IMAGEN (MÁXIMO 255 CARACTERES)
    @Column(name = "comprobante", length = 255)
    private String comprobante;

    @Column(name = "numero_nequi")
    private String numeroNequi;

    @Column(name = "motivo_cancelacion", length = 255)
    private String motivoCancelacion;

    public Pedido() {}

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

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

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }
}