package com.pastelarte.pastelarte_api.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeContactoResponseDTO {

    private Integer idMensaje;
    private String nombre;
    private String correo;
    private String telefono;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean leido;
}
