package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;

import java.util.List;

public interface IMascotaService {
    List<Mascota> obtenerDatos();
    Mascota encontrarPorId(Long id);
    void save (Mascota mascotaGuardado);
    void deleteMascota(Long id);
    void editarMascota(Long id, Mascota mascotaEditado);
}
