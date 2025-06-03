package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.model.MascotaPreferencia;
import com.example.HuellasYyo.service.MascotaPreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotaPreferencia")
public class MascotaPreferenciaController {
    private final MascotaPreferenciaService mascotaPreferenciaService;

    @Autowired
    public MascotaPreferenciaController(MascotaPreferenciaService mascotaPreferenciaService) {
        this.mascotaPreferenciaService = mascotaPreferenciaService;
    }
    @GetMapping
    public List<MascotaPreferencia> obtenerMascotaPreferencia() {
        return mascotaPreferenciaService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaPreferencia> obtenerPorId(@PathVariable("id") Long id) {
        MascotaPreferencia mascotaPreferencia = mascotaPreferenciaService.encontrarPorId(id);
        if (mascotaPreferencia != null) {
            return ResponseEntity.ok(mascotaPreferencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearMascotaPreferencia(@RequestBody MascotaPreferencia info) {
        mascotaPreferenciaService.save(info);
        return ResponseEntity.ok("Preferencia de mascota creada con éxito");

    }
    @PutMapping("/editarMascotaPreferencia/{id}")
    public ResponseEntity<String> editarMascotaPreferencia(@PathVariable Long id, @RequestBody MascotaPreferencia mascotaPreferenciaEditado) {
        try {
            mascotaPreferenciaService.editarMascotaPreferencia(id, mascotaPreferenciaEditado);
            return ResponseEntity.ok("Preferencia de mascota editada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/borrarMascotaPreferencia/{id}")
    public ResponseEntity<String> eliminarMascotaPreferencia(@PathVariable Long id) {
        mascotaPreferenciaService.deletMascotaPreferencia(id);
        return ResponseEntity.ok("Preferencia de mascota eliminada con éxito");
    }
}
