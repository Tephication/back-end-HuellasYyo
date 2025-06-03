package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;

import java.util.List;

public interface IConvivenciaPreferenciaService {
    List<ConvivenciaPreferencia> obtenerDatos();
    ConvivenciaPreferencia encontrarPorId(Long id);
    void save (ConvivenciaPreferencia convivenciaPreferenciaGuardado);
    void deleteConvivenciaPreferencia(Long id);
    void editarConvivenciaPreferencia(Long id, ConvivenciaPreferencia convivenciaPreferenciaEditado);
}
