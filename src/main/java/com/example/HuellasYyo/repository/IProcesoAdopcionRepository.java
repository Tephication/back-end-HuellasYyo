package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.ProcesoAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcesoAdopcionRepository extends JpaRepository<ProcesoAdopcion,Long> {
}
