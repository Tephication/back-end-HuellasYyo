package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.ProcesoAdopcion;

import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.repository.IMascotaRepository;
import com.example.HuellasYyo.repository.IProcesoAdopcionRepository;
import com.example.HuellasYyo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesoAdopcionService implements IProcesoAdopcionService {
    private final IProcesoAdopcionRepository procesoAdopcionRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IMascotaRepository mascotaRepository;

    @Autowired
    public ProcesoAdopcionService(IProcesoAdopcionRepository procesoAdopcionRepository, IUsuarioRepository usuarioRepository, IMascotaRepository mascotaRepository) {
        this.procesoAdopcionRepository = procesoAdopcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<ProcesoAdopcion> obtenerDatos() {
        return procesoAdopcionRepository.findAll();
    }

    @Override
    public ProcesoAdopcion encontrarPorId(Long id) {
        return procesoAdopcionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Long idUsuario, Long idMascota, String estado) {
        // Buscar si ya existe un proceso de adopción para este usuario y mascota
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        Mascota mascota = mascotaRepository.findById(idMascota).orElse(null);
        Optional<ProcesoAdopcion> procesoExistente = procesoAdopcionRepository
                .findByUsuarioAndMascota(usuario, mascota);

        ProcesoAdopcion proceso;

        if (procesoExistente.isPresent()) {
            // Si existe, actualizamos el estado
            proceso = procesoExistente.get();
            proceso.setEstado(estado);
        } else {
            // Si no existe, creamos uno nuevo
            proceso = new ProcesoAdopcion();
            proceso.setUsuario(usuario);
            proceso.setMascota(mascota);
            proceso.setEstado(estado);
        }
        // Guardar el proceso (nuevo o actualizado)
        procesoAdopcionRepository.save(proceso);
    }

    @Override
    public void deleteProcesoAdopcion(Long id) {
        procesoAdopcionRepository.deleteById(id);

    }

    @Override
    public void editarProcesoAdopcion(Long id, ProcesoAdopcion procesoAdopcionEditado) {
        ProcesoAdopcion procesoAdopcionExistente = procesoAdopcionRepository.findById(id).orElse(null);
        if (procesoAdopcionExistente != null) {
            procesoAdopcionExistente.setEstado(procesoAdopcionEditado.getEstado());
            procesoAdopcionExistente.setUrlCertificadoIngresos(procesoAdopcionEditado.getUrlCertificadoIngresos());
            procesoAdopcionExistente.setUrlFormAdopcion(procesoAdopcionEditado.getUrlFormAdopcion());
            procesoAdopcionExistente.setUrlVideo(procesoAdopcionEditado.getUrlVideo());
            procesoAdopcionExistente.setUrlCedula(procesoAdopcionEditado.getUrlCedula());
            procesoAdopcionExistente.setUrlAntecedentesPenales(procesoAdopcionEditado.getUrlAntecedentesPenales());


            procesoAdopcionRepository.save(procesoAdopcionExistente);
        } else {
            throw new RuntimeException("Proceso de adopción no encontrado con el Id " + id);
        }

    }
}
