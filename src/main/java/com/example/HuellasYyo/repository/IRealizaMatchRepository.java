package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.RealizaMatch;
import com.example.HuellasYyo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRealizaMatchRepository extends JpaRepository<RealizaMatch, Long> {
    @Query("SELECT rm FROM RealizaMatch rm " +
            "JOIN FETCH rm.mascota m " +
            "WHERE rm.usuario.id = :idUsuario " +
            "AND rm.matchMascota IS NULL " +
            "AND rm.porcentajeAfinidad >= 50 " +
            "AND m.disponibilidad = true")
    List<RealizaMatch> findMatchPendientesByUsuarioId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT rm FROM RealizaMatch rm " +
            "JOIN FETCH rm.mascota m " +
            "WHERE rm.usuario.id = :idUsuario " +
            "AND rm.matchMascota IS TRUE " +
            "AND m.disponibilidad = true")
    List<RealizaMatch> findMatchByUsuarioId(@Param("idUsuario") Long idUsuario);

    Optional<RealizaMatch> findByUsuarioAndMascota(Usuario usuario, Mascota mascota);

    List<RealizaMatch> findByMascota(Mascota mascota);

    List<RealizaMatch> findByUsuario(Usuario usuario);

}
