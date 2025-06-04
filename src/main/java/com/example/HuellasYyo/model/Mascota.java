package com.example.HuellasYyo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RealizaMatch> realizaMatches = new ArrayList<>();

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProcesoAdopcion> procesoAdopcions = new ArrayList<>();
}
