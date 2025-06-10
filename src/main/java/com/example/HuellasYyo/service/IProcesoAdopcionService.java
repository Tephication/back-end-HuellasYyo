package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.ProcesoAdopcion;

import java.util.List;

public interface IProcesoAdopcionService {
    List<ProcesoAdopcion> obtenerDatos();
    ProcesoAdopcion encontrarPorId(Long id);
    void save (Long idUsuario, Long idMascota, String estado);
    void deleteProcesoAdopcion(Long id);
    void editarProcesoAdopcion(Long id, ProcesoAdopcion procesoAdopcionEditado);
}
