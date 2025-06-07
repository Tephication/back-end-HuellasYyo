package com.example.HuellasYyo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEditadoDto {
    private Long idUsuario;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String urlImagenUsuario;
    private String contrasena;

}
