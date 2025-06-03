package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascota")
public class MascotaController {
    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }
    @GetMapping
    public List<Mascota> obtenerMascota() {
        return mascotaService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.encontrarPorId(id);
        if (mascota != null) {
            return ResponseEntity.ok(mascota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearMascota(@RequestBody Mascota info) {
        mascotaService.save(info);
        return ResponseEntity.ok("Mascota creada con éxito");

    }
    @PutMapping("/editarMascota/{id}")
    public ResponseEntity<String> editarMascota(@PathVariable Long id, @RequestBody Mascota mascotaEditado) {
        try {
            mascotaService.editarMascota(id, mascotaEditado);
            return ResponseEntity.ok("Mascota editada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/borrarMascota/{id}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return ResponseEntity.ok("Mascota eliminada con éxito");
    }
}
