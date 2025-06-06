package com.example.HuellasYyo.dto;

import com.example.HuellasYyo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String token;
    private Usuario usuario;
}
