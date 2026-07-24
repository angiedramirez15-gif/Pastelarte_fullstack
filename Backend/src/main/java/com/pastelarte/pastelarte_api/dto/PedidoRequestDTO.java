package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    private Integer idCliente;
    private LocalDate fecha;
    private String estado;
    private Double total;
    private Integer idPago;
    private String comprobante;
    private String numeroNequi;
}