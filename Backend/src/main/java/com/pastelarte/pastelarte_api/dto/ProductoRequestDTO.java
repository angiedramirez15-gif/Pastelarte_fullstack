// ProductoRequestDTO.java
package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
    private String imagen;
    private String porciones;
}