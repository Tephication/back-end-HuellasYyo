package com.example.HuellasYyo.repository;

import com.example.HuellasYyo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
}
