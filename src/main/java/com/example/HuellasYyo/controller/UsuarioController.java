package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.JwtUtil;
import com.example.HuellasYyo.dto.UsuarioDto;
import com.example.HuellasYyo.dto.UsuarioEditadoDto;
import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario info) {
        usuarioService.save(info);
        return ResponseEntity.ok("Usuario creado con éxito");
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
            UsuarioDto usuarioDto = new UsuarioDto(token,usuario);
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

}
