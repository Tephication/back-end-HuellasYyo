package com.example.HuellasYyo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(nullable = false,length = 30)
    private String caracterPreferencia;
    @Column(nullable = false,length = 30)
    private String sexoPreferencia;
    @Column(nullable = false,length = 50)
    private String especieBuscada;
    @Column(nullable = false,length = 50)
    private String edadPreferencia;
    @Column(nullable = false,length = 30)
    private String tamanoPreferencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkIdUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

}
