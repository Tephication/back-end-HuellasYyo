package com.example.HuellasYyo.service;

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
    public void editarUsuario(Long id, Usuario usuarioEditado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null){
            usuarioExistente.setNombreCompleto(usuarioEditado.getNombreCompleto());
            usuarioExistente.setTelefono(usuarioEditado.getTelefono());
            usuarioExistente.setCorreo(usuarioEditado.getCorreo());
            usuarioExistente.setContrasena(usuarioEditado.getContrasena());
            usuarioExistente.setUrlImagenUsuario(usuarioEditado.getUrlImagenUsuario());
            usuarioExistente.setFechaNacimiento(usuarioEditado.getFechaNacimiento());
            usuarioExistente.setEstado(usuarioEditado.isEstado());
            usuarioExistente.setTipoUsuario(usuarioEditado.getTipoUsuario());

            usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con el Id " + id);
        }

    }

    // Métdo de carga de usuario implementado desde UserDetailsService
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
