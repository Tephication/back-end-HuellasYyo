package com.example.HuellasYyo.service;

import com.example.HuellasYyo.dto.UsuarioDto;
import com.example.HuellasYyo.dto.UsuarioEditadoDto;
import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.repository.IUsuarioRepository;

import com.example.HuellasYyo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsuarioService implements IUsuarioService{
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerDatos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario encontrarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Usuario usuarioGuardado) {
        usuarioGuardado.setContrasena(passwordEncoder.encode(usuarioGuardado.getContrasena()));
        usuarioRepository.save(usuarioGuardado);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(Long id, UsuarioEditadoDto usuarioDto) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setNombreCompleto(usuarioDto.getNombreCompleto());
            usuarioExistente.setTelefono(usuarioDto.getTelefono());
            usuarioExistente.setCorreo(usuarioDto.getCorreo());

            // Solo codifica y actualiza si viene una nueva contraseña no vacía
            if (usuarioDto.getContrasena() != null && !usuarioDto.getContrasena().isBlank()) {
                usuarioExistente.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
            }

            usuarioExistente.setUrlImagenUsuario(usuarioDto.getUrlImagenUsuario());

            usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con el Id " + id);
        }
    }

    // Método de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByCorreo(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.buscarPorCorreo(correo);
        if (user == null) {
            throw new UsernameNotFoundException("Correo no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getContrasena(), new ArrayList<>());
    }

    public Usuario loadUserByCorreoComplete(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.buscarPorCorreo(correo);
        if (user == null) {
            throw new UsernameNotFoundException("Correo no encontrado");
        }
        return user;
    }
}
