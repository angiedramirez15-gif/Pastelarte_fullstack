package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoResponseDTO {

    private Integer idPago;
    private String tipo;
    private String detalle;
}