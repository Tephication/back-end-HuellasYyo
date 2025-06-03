package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.ProcesoAdopcion;

import java.util.List;

public interface IProcesoAdopcionService {
    List<ProcesoAdopcion> obtenerDatos();
    ProcesoAdopcion encontrarPorId(Long id);
    void save (ProcesoAdopcion procesoAdopcionGuardado);
    void deleteProcesoAdopcion(Long id);
    void editarProcesoAdopcion(Long id, ProcesoAdopcion procesoAdopcionEditado);
}
