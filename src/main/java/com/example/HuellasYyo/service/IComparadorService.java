package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.MascotaPreferencia;
import org.springframework.stereotype.Service;

@Service
public interface IComparadorService {
    double calcularCompatibilidad(Mascota mascota, MascotaPreferencia preferencia);
}
