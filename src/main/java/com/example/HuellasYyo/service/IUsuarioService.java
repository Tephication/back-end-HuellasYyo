package com.example.HuellasYyo.service;

import com.example.HuellasYyo.dto.PreferenciasDTO;
import com.example.HuellasYyo.dto.UsuarioEditadoDto;
import com.example.HuellasYyo.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> obtenerDatos();
    Usuario encontrarPorId(Long id);
    void save (Usuario usuarioGuardado);
    void deleteUsuario(Long id);
    void editarUsuario(Long id, UsuarioEditadoDto usuarioDto);
    void agregarPreferenciasAlUsuario(Long idUsuario, PreferenciasDTO preferenciasDTO);
    PreferenciasDTO encontrarPreferenciasPorId(Long idUsuario);
}
