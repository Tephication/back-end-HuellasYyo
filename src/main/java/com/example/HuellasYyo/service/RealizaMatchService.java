package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.MascotaPreferencia;
import com.example.HuellasYyo.model.RealizaMatch;

import com.example.HuellasYyo.model.Usuario;
import com.example.HuellasYyo.repository.IMascotaRepository;
import com.example.HuellasYyo.repository.IRealizaMatchRepository;
import com.example.HuellasYyo.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RealizaMatchService implements IRealizaMatchService {
    private final IRealizaMatchRepository realizaMatchRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ComparadorService comparadorService;
    private final IMascotaRepository mascotaRepository;

    @Autowired
    public RealizaMatchService(IRealizaMatchRepository realizaMatchRepository, IUsuarioRepository usuarioRepository, ComparadorService comparadorService, IMascotaRepository mascotaRepository) {
        this.realizaMatchRepository = realizaMatchRepository;
        this.usuarioRepository = usuarioRepository;
        this.comparadorService = comparadorService;
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    public List<RealizaMatch> obtenerDatos() {
        return realizaMatchRepository.findAll();
    }

    @Override
    public RealizaMatch encontrarPorId(Long id) {
        return realizaMatchRepository.findById(id).orElse(null);
    }

    @Override
    public void save(RealizaMatch realizaMatchGuardado) {
        realizaMatchRepository.save(realizaMatchGuardado);

    }

    @Override
    public void deleteRealizaMatch(Long id) {
        realizaMatchRepository.deleteById(id);
    }

    @Override
    public void editarRealizaMatch(Long id, RealizaMatch realizaMatchEditado) {
        RealizaMatch realizaMatchExistente = realizaMatchRepository.findById(id).orElse(null);
        if (realizaMatchExistente != null) {
            realizaMatchExistente.setMatchMascota(realizaMatchEditado.getMatchMascota());
            realizaMatchExistente.setPorcentajeAfinidad(realizaMatchEditado.getPorcentajeAfinidad());

            realizaMatchRepository.save(realizaMatchExistente);
        } else {
            throw new RuntimeException("Realiza match no encontrado con el Id " + id);
        }

    }

    @Override
    public List<RealizaMatch> posibleMatch(Long id) {
        return realizaMatchRepository.findMatchPendientesByUsuarioId(id);
    }

    @Override
    public void like(Long id, Boolean reaccion) {
        RealizaMatch realizaMatchExistente = realizaMatchRepository.findById(id).orElse(null);
        if (realizaMatchExistente != null) {
            realizaMatchExistente.setMatchMascota(reaccion);

            realizaMatchRepository.save(realizaMatchExistente);
        } else {
            throw new RuntimeException("Realiza match no encontrado con el Id " + id);
        }
    }

    @Override
    public List<RealizaMatch> buscarMatches(Long id) {
        return realizaMatchRepository.findMatchByUsuarioId(id);
    }

    @Override
    @Transactional
    public void actualizarMatchesPorNuevaMascota(Mascota mascota) {
        List<Usuario> usuarios = usuarioRepository.findByTipoUsuarioNot("Admin");
        for (Usuario usuario : usuarios) {
            MascotaPreferencia pref = usuario.getMascotaPreferencia();
            double porcentaje = comparadorService.calcularCompatibilidad(mascota, pref);
            RealizaMatch match = new RealizaMatch();
            match.setMatchMascota(null);
            match.setPorcentajeAfinidad(BigDecimal.valueOf(porcentaje));
            match.setUsuario(usuario);
            match.setMascota(mascota);
            realizaMatchRepository.save(match);
        }
    }

    @Override
    @Transactional
    public void actualizarMatchesPorNuevaPreferencia(Usuario usuario) {
        MascotaPreferencia pref = usuario.getMascotaPreferencia();
        List<Mascota> mascotas = mascotaRepository.encontrarMascotasDisponibles();
        for (Mascota mascota : mascotas) {
            double porcentaje = comparadorService.calcularCompatibilidad(mascota, pref);
            RealizaMatch match = realizaMatchRepository.findByUsuarioAndMascota(usuario, mascota)
                    .orElse(null);
            if (match == null) {
                match = new RealizaMatch();
                match.setMatchMascota(null);
                match.setUsuario(usuario);
                match.setMascota(mascota);
            }
            match.setPorcentajeAfinidad(BigDecimal.valueOf(porcentaje));
            realizaMatchRepository.save(match);
        }
    }

    @Override
    @Transactional
    public void actualizarMatchesPorEdicionMascota(Mascota mascota) {
        List<RealizaMatch> matches = realizaMatchRepository.findByMascota(mascota);
        if (matches.isEmpty()) {
            actualizarMatchesPorNuevaMascota(mascota);
            return;
        }
        for (RealizaMatch match : matches) {
            Usuario usuario = match.getUsuario();
            double porcentaje = comparadorService.calcularCompatibilidad(mascota, usuario.getMascotaPreferencia());
            match.setPorcentajeAfinidad(BigDecimal.valueOf(porcentaje));
            realizaMatchRepository.save(match);
        }
    }

    @Override
    @Transactional
    public void actualizarMatchesPorEdicionUsuario(Usuario usuario) {
        List<RealizaMatch> matches = realizaMatchRepository.findByUsuario(usuario);
        List<Mascota> mascotas = mascotaRepository.encontrarMascotasDisponibles();
        if (matches.isEmpty()) {
            actualizarMatchesPorNuevaPreferencia(usuario);
            return;
        }
        for (Mascota mascota : mascotas) {
            RealizaMatch match = realizaMatchRepository.findByUsuarioAndMascota(usuario, mascota)
                    .orElse(null);
            if (match == null) {
                match = new RealizaMatch();
                match.setMatchMascota(null);
                match.setUsuario(usuario);
                match.setMascota(mascota);
            }
            double porcentaje = comparadorService.calcularCompatibilidad(mascota, usuario.getMascotaPreferencia());
            match.setPorcentajeAfinidad(BigDecimal.valueOf(porcentaje));
            realizaMatchRepository.save(match);
        }
    }

}
