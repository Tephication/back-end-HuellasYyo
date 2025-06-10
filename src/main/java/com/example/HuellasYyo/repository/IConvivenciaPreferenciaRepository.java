package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConvivenciaPreferenciaRepository extends JpaRepository<ConvivenciaPreferencia,Long> {
    ConvivenciaPreferencia findByUsuario_IdUsuario(Long idUsuario);
}
