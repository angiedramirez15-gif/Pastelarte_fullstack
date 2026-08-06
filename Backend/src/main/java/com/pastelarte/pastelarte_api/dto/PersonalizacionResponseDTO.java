// PersonalizacionResponseDTO.java
package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalizacionResponseDTO {

    private Integer idPersonalizacion;
    private String tamano;
    private String sabor;
    private String decoraciones;
    private String descripcion;
    private BigDecimal costoExtra;
    private String imagen;
}