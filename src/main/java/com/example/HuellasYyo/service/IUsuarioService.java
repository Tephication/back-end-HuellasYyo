package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> obtenerDatos();
    Usuario encontrarPorId(Long id);
    void save (Usuario usuarioGuardado);
    void deleteUsuario(Long id);
    void editarUsuario(Long id, Usuario usuarioEditado);
}
