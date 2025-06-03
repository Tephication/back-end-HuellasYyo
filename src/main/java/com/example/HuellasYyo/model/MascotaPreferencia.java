package com.example.HuellasYyo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MascotaPreferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascotaPreferencia;
    @Column(nullable = false,length = 10)
    private String caracterPreferencia;
    @Column(nullable = false,length = 10)
    private String sexoPreferencia;
    @Column(nullable = false,length = 50)
    private String especieBuscada;
    @Column(nullable = false,length = 50)
    private String edadPreferencia;
    @Column(nullable = false,length = 10)
    private String tamanoPreferencia;
    @Column(nullable = false)
    private Long fkIdUsuario;
}
