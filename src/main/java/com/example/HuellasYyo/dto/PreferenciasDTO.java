package com.example.HuellasYyo.dto;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import com.example.HuellasYyo.model.MascotaPreferencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenciasDTO {
    // Datos de convivencia
    private ConvivenciaPreferencia convivencia;
    private MascotaPreferencia mascotaPreferencia;
    private LocalDate fechaNacimiento;
}
