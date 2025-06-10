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

    @PostMapping("/usuario/{idUsuario}/mascota/{idMascota}/estado/{estado}")
    public ResponseEntity<String> crearProcesoAdopcion(
            @PathVariable Long idUsuario,
            @PathVariable Long idMascota,
            @PathVariable String estado) {

        // Validación básica de los path variables
        if (idUsuario == null || idMascota == null || estado == null || estado.isBlank()) {
            return ResponseEntity.badRequest().body("Parámetros inválidos");
        }

        // Validar el estado (ajusta según tus valores permitidos)
        if (!List.of("PENDIENTE", "APROBADO", "RECHAZADO").contains(estado.toUpperCase())) {
            return ResponseEntity.badRequest().body("Estado no válido");
        }

        try {
            procesoAdopcionService.save(idUsuario, idMascota,estado);
            return ResponseEntity.ok("Proceso de adopción guardado con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar el proceso: " + e.getMessage());
        }
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
