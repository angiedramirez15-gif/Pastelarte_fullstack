package com.pastelarte.pastelarte_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoRequestDTO {

    @NotNull(message = "El idCliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.00", message = "El total debe ser mayor o igual a 0.00")
    private BigDecimal total;

    @NotNull(message = "El idPago es obligatorio")
    private Integer idPago;

    private String comprobante;

    private String numeroNequi;

    // VALIDACIÓN: El pedido DEBE incluir al menos un producto/detalle
    @NotEmpty(message = "Un pedido debe tener al menos un detalle de producto")
    private List<@Valid DetallePedidoRequestDTO> detalles;

    // Getters y Setters

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

    public List<DetallePedidoRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequestDTO> detalles) {
        this.detalles = detalles;
    }
}