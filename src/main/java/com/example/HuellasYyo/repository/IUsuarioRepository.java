package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    Usuario buscarPorCorreo(@Param("correo") String correo);

    List<Usuario> findByTipoUsuarioNot(String tipoUsuario);
}
