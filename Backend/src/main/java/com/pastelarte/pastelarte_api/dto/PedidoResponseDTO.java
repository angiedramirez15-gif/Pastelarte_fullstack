// PedidoResponseDTO.java
package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private Integer idPedido;
    private Integer idCliente;
    private String nombreCliente;
    private LocalDate fecha;
    private String estado;
    private BigDecimal total;
    private Integer idPago;
    private String comprobante;
    private String numeroNequi;
    private String motivoCancelacion;
}