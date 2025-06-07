package com.example.HuellasYyo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RealizaMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRealizaMatch;
    @Column(nullable = true)
    private Boolean matchMascota;
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal porcentajeAfinidad;

    @ManyToOne
    @JoinColumn(name = "fkidUsuario")
    @JsonIgnoreProperties({"realizaMatches", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkidMascota")
    @JsonIgnoreProperties({"realizaMatches", "hibernateLazyInitializer", "handler"})
    private Mascota mascota;


}
