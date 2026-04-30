package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoResponseDTO {

    private Integer idDetalle;
    private Integer idPedido;
    private Integer idProducto;
    private Integer idPersonalizacion;
    private Integer cantidad;
    private Float subtotal;
}