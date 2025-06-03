package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.model.ProcesoAdopcion;
import com.example.HuellasYyo.service.ProcesoAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procesoAdopcion")
public class ProcesoAdopcionController {
    private final ProcesoAdopcionService procesoAdopcionService;

    @Autowired
    public ProcesoAdopcionController(ProcesoAdopcionService procesoAdopcionService) {
        this.procesoAdopcionService = procesoAdopcionService;
    }

    @GetMapping
    public List<ProcesoAdopcion> obtenerProcesoAdopcion() {
        return procesoAdopcionService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoAdopcion> obtenerPorId(@PathVariable("id") Long id) {
        ProcesoAdopcion procesoAdopcion = procesoAdopcionService.encontrarPorId(id);
        if (procesoAdopcion != null) {
            return ResponseEntity.ok(procesoAdopcion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearProcesoAdopcion(@RequestBody ProcesoAdopcion info) {
        procesoAdopcionService.save(info);
        return ResponseEntity.ok("Proceso de adopción creado con éxito");

    }
    @PutMapping("/editarProcesoAdopcion/{id}")
    public ResponseEntity<String> editarProcesoAdopcion(@PathVariable Long id, @RequestBody ProcesoAdopcion procesoAdopcionEditado) {
        try {
            procesoAdopcionService.editarProcesoAdopcion(id, procesoAdopcionEditado);
            return ResponseEntity.ok("Proceso de adopción editado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/borrarProcesoAdopcion/{id}")
    public ResponseEntity<String> eliminarProcesoAdopcion(@PathVariable Long id) {
        procesoAdopcionService.deleteProcesoAdopcion(id);
        return ResponseEntity.ok("Proceso de adopción eliminado con éxito");
    }


}
