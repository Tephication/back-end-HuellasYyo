package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.ProcesoAdopcion;
import com.example.HuellasYyo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProcesoAdopcionRepository extends JpaRepository<ProcesoAdopcion, Long> {
    Optional<ProcesoAdopcion> findByUsuarioAndMascota(Usuario usuario, Mascota mascota);
}
