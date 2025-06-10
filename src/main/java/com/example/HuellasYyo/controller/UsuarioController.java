package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.JwtUtil;
import com.example.HuellasYyo.dto.PreferenciasDTO;
import com.example.HuellasYyo.dto.UsuarioDto;
import com.example.HuellasYyo.dto.UsuarioEditadoDto;
import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import com.example.HuellasYyo.model.MascotaPreferencia;
import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.encontrarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("traerpreferencias/{idUsuario}")
    public ResponseEntity<PreferenciasDTO> obtenerPreferenciasPorUsuario(@PathVariable Long idUsuario) {
        PreferenciasDTO preferencias = usuarioService.encontrarPreferenciasPorId(idUsuario);

        if (preferencias.getConvivencia() == null && preferencias.getMascotaPreferencia() == null) {
            return ResponseEntity.ok(new PreferenciasDTO());
        }

        return ResponseEntity.ok(preferencias);
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario info) {
        usuarioService.save(info);
        return ResponseEntity.ok("Usuario creado con éxito");
    }

    @PostMapping("/preferencias/{id}")
    public ResponseEntity<String> recibirPreferencias(@PathVariable Long id, @RequestBody PreferenciasDTO preferenciasDTO) {
        try {
            usuarioService.agregarPreferenciasAlUsuario(id, preferenciasDTO);
            return ResponseEntity.ok("Preferencias recibidas correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarUsuario/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable Long id, @RequestBody UsuarioEditadoDto usuarioEditado) {
        System.out.println("📩 Recibida solicitud para editar usuario con ID: " + id);
        try {
            usuarioService.editarUsuario(id, usuarioEditado);
            return ResponseEntity.ok("Usuario editado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/preferencias")
    public ResponseEntity<?> actualizarPreferencias(
            @PathVariable Long id,
            @RequestBody PreferenciasDTO preferenciasDTO) {

        try {
            usuarioService.actualizarOAgregarPreferencias(id, preferenciasDTO);
            return ResponseEntity.ok("Preferencias actualizadas correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar las preferencias");
        }
    }

    @DeleteMapping("/borrarUsuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        UserDetails userDetails = usuarioService.loadUserByCorreo(user.getCorreo());
        if (userDetails != null && passwordEncoder.matches(user.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            Usuario usuario = usuarioService.loadUserByCorreoComplete(user.getCorreo());
            UsuarioDto usuarioDto = new UsuarioDto(token, usuario);
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

}
