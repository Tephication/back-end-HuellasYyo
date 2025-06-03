package com.example.HuellasYyo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(nullable = false,length = 100)
    private String nombreCompleto;
    @Column(nullable = false,length = 11)
    private String telefono;
    @Column(nullable = false,length = 100)
    private String correo;
    @Column(nullable = false)
    private String contrasena;
    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String urlImagenUsuario;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false,length = 20)
    private String tipoUsuario;
}
