package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.MascotaPreferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMascotaPreferenciaRepository extends JpaRepository<MascotaPreferencia,Long> {
    MascotaPreferencia findByUsuario_IdUsuario(Long idUsuario);
}
