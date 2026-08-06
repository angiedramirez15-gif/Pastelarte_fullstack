// PersonalizacionRequestDTO.java
package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalizacionRequestDTO {

    private String tamano;
    private String sabor;
    private String decoraciones;
    private String descripcion;
    private BigDecimal costoExtra;
    private String imagen;
    private Integer idCliente;
    private String estado;
}