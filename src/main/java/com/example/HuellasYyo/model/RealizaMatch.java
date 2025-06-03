package com.example.HuellasYyo.model;

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
    private boolean matchMascota;
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal porcentajeAfinidad;
    @Column(nullable = false)
    private Long fkIdUsuario;
    @Column(nullable = false)
    private Long fkIdMascota;

}
