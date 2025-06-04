package com.example.HuellasYyo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProcesoAdopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesoAdopcion;
    @Column(nullable = false,length = 50)
    private String estado;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlCertificadoIngresos;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlFormAdopcion;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlVideo;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlCedula;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlAntecedentesPenales;


    @ManyToOne
    @JoinColumn(name = "fkidUsuario")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkidMascota")
    @JsonBackReference
    private Mascota mascota;
}
