package com.example.HuellasYyo.service;

import com.example.HuellasYyo.dto.PreferenciasDTO;
import com.example.HuellasYyo.dto.UsuarioEditadoDto;
import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import com.example.HuellasYyo.model.MascotaPreferencia;
import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.repository.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final IConvivenciaPreferenciaRepository convivenciaRepo;
    private final IMascotaPreferenciaRepository mascotaRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, IConvivenciaPreferenciaRepository convivenciaRepo, IMascotaPreferenciaRepository mascotaRepo) {
        this.usuarioRepository = usuarioRepository;
        this.convivenciaRepo = convivenciaRepo;
        this.mascotaRepo = mascotaRepo;
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

    @Transactional
    public void agregarPreferenciasAlUsuario(Long idUsuario, PreferenciasDTO preferenciasDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));

        // Obtener las preferencias desde el DTO
        ConvivenciaPreferencia convivencia = preferenciasDTO.getConvivencia();
        MascotaPreferencia mascota = preferenciasDTO.getMascotaPreferencia();

        // Establecer la relación hacia el usuario ANTES de guardar
        convivencia.setUsuario(usuario);
        mascota.setUsuario(usuario); // solo si MascotaPreferencia tiene relación con Usuario

        // Guardar las preferencias
        convivencia = convivenciaRepo.save(convivencia);
        mascota = mascotaRepo.save(mascota);

        // Establecer las preferencias en el usuario (opcional, si necesitas que se refleje en el modelo)
        usuario.setConvivenciaPreferencia(convivencia);
        usuario.setMascotaPreferencia(mascota);
        usuario.setFechaNacimiento(preferenciasDTO.getFechaNacimiento());

        // Guardar el usuario (aunque en este flujo, no sería estrictamente necesario si sólo lees)
        usuarioRepository.save(usuario);
    }

    @Override
    public PreferenciasDTO encontrarPreferenciasPorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuario == null) {
            return new PreferenciasDTO(); // Usuario no existe, devuelve DTO vacío
        }

        // Intentamos buscar las preferencias, si no existen se quedan en null
        ConvivenciaPreferencia convivencia = convivenciaRepo.findByUsuario_IdUsuario(idUsuario);
        MascotaPreferencia mascota = mascotaRepo.findByUsuario_IdUsuario(idUsuario);

        return new PreferenciasDTO(convivencia, mascota, usuario.getFechaNacimiento());
    }

    @Transactional
    public void actualizarOAgregarPreferencias(Long idUsuario, PreferenciasDTO dto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        boolean tieneConvivencia = usuario.getConvivenciaPreferencia() != null;
        boolean tieneMascota = usuario.getMascotaPreferencia() != null;

        if (tieneConvivencia && tieneMascota) {
            ConvivenciaPreferencia convivencia = usuario.getConvivenciaPreferencia();
            MascotaPreferencia mascota = usuario.getMascotaPreferencia();

            // Actualizar campos de Convivencia
            convivencia.setExperiencia(dto.getConvivencia().isExperiencia());
            convivencia.setDedicacionTiempo(dto.getConvivencia().getDedicacionTiempo());
            convivencia.setCompatibilidadNinos(dto.getConvivencia().isCompatibilidadNinos());
            convivencia.setCompatibilidadMascotas(dto.getConvivencia().isCompatibilidadMascotas());
            convivencia.setTipoVivienda(dto.getConvivencia().getTipoVivienda());
            convivencia.setAdiestramientoOfrecido(dto.getConvivencia().getAdiestramientoOfrecido());
            convivencia.setViajesEnAuto(dto.getConvivencia().isViajesEnAuto());

            // Actualizar campos de MascotaPreferencia
            mascota.setCaracterPreferencia(dto.getMascotaPreferencia().getCaracterPreferencia());
            mascota.setSexoPreferencia(dto.getMascotaPreferencia().getSexoPreferencia());
            mascota.setEspecieBuscada(dto.getMascotaPreferencia().getEspecieBuscada());
            mascota.setEdadPreferencia(dto.getMascotaPreferencia().getEdadPreferencia());
            mascota.setTamanoPreferencia(dto.getMascotaPreferencia().getTamanoPreferencia());

            // Actualizar fecha de nacimiento
            usuario.setFechaNacimiento(dto.getFechaNacimiento());

            // Guardar cambios
            convivenciaRepo.save(convivencia);
            mascotaRepo.save(mascota);
            usuarioRepository.save(usuario);
        } else {
            // Si alguna preferencia no existe, se crea desde cero
            agregarPreferenciasAlUsuario(idUsuario, dto);
        }
    }

}
