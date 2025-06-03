package com.example.HuellasYyo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    @Column(nullable = false,length = 100)
    private String nombre;
    @Column(nullable = false,length = 10)
    private String especie;
    @Column(nullable = false,length = 10)
    private String sexo;
    @Column(nullable = false,length = 10)
    private String caracter;
    @Column(nullable = false,length = 2)
    private String edad;
    @Column(nullable = false,length = 10)
    private String tamano;
    @Column(nullable = false,length = 100)
    private String raza;
    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String urlImagenMascota;
    @Column(nullable = false)
    private boolean disponibilidad;
    private String otrasCaracteristicas;
}
