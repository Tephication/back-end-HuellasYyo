package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.RealizaMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRealizaMatchRepository extends JpaRepository<RealizaMatch,Long> {
    @Query("SELECT rm FROM RealizaMatch rm " +
            "JOIN FETCH rm.mascota " +
            "WHERE rm.usuario.id = :idUsuario " +
            "AND rm.matchMascota IS NULL " +
            "AND rm.porcentajeAfinidad >= 50")
    List<RealizaMatch> findMatchPendientesByUsuarioId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT rm FROM RealizaMatch rm " +
            "JOIN FETCH rm.mascota " +
            "WHERE rm.usuario.id = :idUsuario " +
            "AND rm.matchMascota IS TRUE " )

    List<RealizaMatch> findMatchByUsuarioId(@Param("idUsuario") Long idUsuario);
}
