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
public class ConvivenciaPreferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvivencia;
    @Column(nullable = false)
    private boolean experiencia;
    @Column(nullable = false,length = 30)
    private String dedicacionTiempo;
    @Column(nullable = false)
    private boolean compatibilidadNinos;
    @Column(nullable = false)
    private boolean compatibilidadMascotas;
    @Column(nullable = false,length = 30)
    private String tipoVivienda;
    @Column(nullable = false,length = 30)
    private String adiestramientoOfrecido;
    @Column(nullable = false)
    private boolean viajesEnAuto;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkIdUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
}
