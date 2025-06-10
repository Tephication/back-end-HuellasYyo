package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConvivenciaPreferenciaRepository extends JpaRepository<ConvivenciaPreferencia,Long> {
    ConvivenciaPreferencia findByUsuario_IdUsuario(Long idUsuario);
}
