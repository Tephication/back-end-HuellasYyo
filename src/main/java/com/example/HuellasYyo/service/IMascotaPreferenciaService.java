package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.MascotaPreferencia;

import java.util.List;

public interface IMascotaPreferenciaService {
    List<MascotaPreferencia> obtenerDatos();
    MascotaPreferencia encontrarPorId(Long id);
    void save (MascotaPreferencia mascotaPreferenciaGuardado);
    void deletMascotaPreferencia(Long id);
    void editarMascotaPreferencia(Long id, MascotaPreferencia mascotaPreferenciaEditado);
}
