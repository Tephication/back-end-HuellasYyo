package com.example.HuellasYyo.controller;

import com.example.HuellasYyo.model.RealizaMatch;
import com.example.HuellasYyo.service.RealizaMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realizaMatch")
public class RealizaMatchController {
    private final RealizaMatchService realizaMatchService;

    @Autowired
    public RealizaMatchController(RealizaMatchService realizaMatchService) {
        this.realizaMatchService = realizaMatchService;
    }


    @GetMapping
    public List<RealizaMatch> obtenerRealizaMatch() {
        return realizaMatchService.obtenerDatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealizaMatch> obtenerPorId(@PathVariable("id") Long id) {
        RealizaMatch realizaMatch = realizaMatchService.encontrarPorId(id);
        if (realizaMatch != null) {
            return ResponseEntity.ok(realizaMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/posibleMatch/{id}")
    public ResponseEntity<List <RealizaMatch>> obtenerPosibleMatch (@PathVariable("id") Long id){
        List <RealizaMatch> realizaMatch = realizaMatchService.posibleMatch(id);
        if (realizaMatch != null) {
            return ResponseEntity.ok(realizaMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarMatches/{id}")
    public ResponseEntity<List <RealizaMatch>> obtenerMatches (@PathVariable("id") Long id){
        List <RealizaMatch> realizaMatch = realizaMatchService.buscarMatches(id);
        if (realizaMatch != null) {
            return ResponseEntity.ok(realizaMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearRealizaMatch(@RequestBody RealizaMatch info) {
        realizaMatchService.save(info);
        return ResponseEntity.ok("Realiza match creado con éxito");

    }
    @PutMapping("/editarRealizaMatch/{id}")
    public ResponseEntity<String> editarRealizaMatch(@PathVariable Long id, @RequestBody RealizaMatch realizaMatchEditado) {
        try {
            realizaMatchService.editarRealizaMatch(id, realizaMatchEditado);
            return ResponseEntity.ok("Realiza match editado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/borrarRealizaMatch/{id}")
    public ResponseEntity<String> eliminarRealizaMatch(@PathVariable Long id) {
        realizaMatchService.deleteRealizaMatch(id);
        return ResponseEntity.ok("Realiza match eliminado con éxito");
    }

    @PatchMapping("/reaccion/{id}")
    public ResponseEntity<String> cambiarReaccion(
            @PathVariable Long id,
            @RequestParam Boolean matchMascota) {

        try {
            realizaMatchService.like(id, matchMascota);
            return ResponseEntity.ok("Realiza match editado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
