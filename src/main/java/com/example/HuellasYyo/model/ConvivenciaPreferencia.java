package com.example.HuellasYyo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConvivenciaPreferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvivencia;
    @Column(nullable = false)
    private boolean experiencia;
    @Column(nullable = false,length = 20)
    private String dedicacionTiempo;
    @Column(nullable = false)
    private boolean compatibilidadNinos;
    @Column(nullable = false)
    private boolean compatibilidadMascotas;
    @Column(nullable = false,length = 20)
    private String tipoVivienda;
    @Column(nullable = false,length = 20)
    private String adiestramientoOfrecido;
    @Column(nullable = false)
    private boolean viajesEnAuto;
    @Column(nullable = false)
    private Long fkIdUsuario;
}
