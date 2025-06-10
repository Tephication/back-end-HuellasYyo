package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Long> {
    @Query("SELECT m FROM Mascota m WHERE m.disponibilidad = true")
    List<Mascota> encontrarMascotasDisponibles();
}
