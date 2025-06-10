package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.RealizaMatch;
import com.example.HuellasYyo.model.Usuario;

import java.util.List;

public interface IRealizaMatchService {
    List<RealizaMatch> obtenerDatos();

    RealizaMatch encontrarPorId(Long id);

    void save(RealizaMatch realizaMatchGuardado);

    void deleteRealizaMatch(Long id);

    void editarRealizaMatch(Long id, RealizaMatch realizaMatchEditado);

    List<RealizaMatch> posibleMatch(Long id);

    void like(Long id, Boolean reaccion);

    List<RealizaMatch> buscarMatches(Long id);

    void actualizarMatchesPorNuevaMascota(Mascota nuevaMascota);
    void actualizarMatchesPorNuevaPreferencia(Usuario usuario);
    void actualizarMatchesPorEdicionMascota(Mascota mascotaEditada);
    void actualizarMatchesPorEdicionUsuario(Usuario usuario);
}
