package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;
import com.example.HuellasYyo.service.ConvivenciaPreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convivenciaPreferencia")
public class ConvivenciaPreferenciaController {
    private final ConvivenciaPreferenciaService convivenciaPreferenciaService;

    @Autowired
    public ConvivenciaPreferenciaController(ConvivenciaPreferenciaService convivenciaPreferenciaService) {
        this.convivenciaPreferenciaService = convivenciaPreferenciaService;
    }
    @GetMapping
    public List<ConvivenciaPreferencia> obtenerConvivenciaPreferencia() {
        return convivenciaPreferenciaService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvivenciaPreferencia> obtenerPorId(@PathVariable("id") Long id) {
        ConvivenciaPreferencia convivenciaPreferencia =convivenciaPreferenciaService.encontrarPorId(id);
        if (convivenciaPreferencia != null) {
            return ResponseEntity.ok(convivenciaPreferencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearConvivenciaPreferencia(@RequestBody ConvivenciaPreferencia info) {
        convivenciaPreferenciaService.save(info);
        return ResponseEntity.ok("Preferencia de convivencia creada con éxito");

    }
    @PutMapping("/editarConvivenciaPreferencia/{id}")
    public ResponseEntity<String> editarConvivenciaPreferencia(@PathVariable Long id, @RequestBody ConvivenciaPreferencia convivenciaPreferenciaEditado) {
        try {
            convivenciaPreferenciaService.editarConvivenciaPreferencia(id, convivenciaPreferenciaEditado);
            return ResponseEntity.ok("Preferencia de convivencia editada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/borrarConvivenciaPreferencia/{id}")
    public ResponseEntity<String> eliminarConvivenciaPreferencia(@PathVariable Long id) {
        convivenciaPreferenciaService.deleteConvivenciaPreferencia(id);
        return ResponseEntity.ok("Preferencia de convivencia eliminada con éxito");
    }
}
