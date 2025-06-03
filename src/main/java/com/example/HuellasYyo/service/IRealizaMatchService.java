package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.RealizaMatch;

import java.util.List;

public interface IRealizaMatchService {
    List<RealizaMatch> obtenerDatos();
    RealizaMatch encontrarPorId(Long id);
    void save (RealizaMatch realizaMatchGuardado);
    void deleteRealizaMatch(Long id);
    void editarRealizaMatch(Long id, RealizaMatch realizaMatchEditado);
}
