// DetallePedidoResponseDTO.java
package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoResponseDTO {

    private Integer idDetalle;
    private Integer idPedido;
    private Integer idProducto;
    private String nombreProducto;
    private Integer idPersonalizacion;
    private PersonalizacionResponseDTO personalizacion;
    private Integer cantidad;
    private BigDecimal subtotal;
}