package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.RealizaMatch;

import com.example.HuellasYyo.repository.IRealizaMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealizaMatchService implements IRealizaMatchService{
    private final IRealizaMatchRepository realizaMatchRepository;

    @Autowired
    public RealizaMatchService(IRealizaMatchRepository realizaMatchRepository) {
        this.realizaMatchRepository = realizaMatchRepository;
    }


    @Override
    public List<RealizaMatch> obtenerDatos() {
        return realizaMatchRepository.findAll();
    }

    @Override
    public RealizaMatch encontrarPorId(Long id) {
        return realizaMatchRepository.findById(id).orElse(null);
    }

    @Override
    public void save(RealizaMatch realizaMatchGuardado) {
        realizaMatchRepository.save(realizaMatchGuardado);

    }

    @Override
    public void deleteRealizaMatch(Long id) {
        realizaMatchRepository.deleteById(id);
    }

    @Override
    public void editarRealizaMatch(Long id, RealizaMatch realizaMatchEditado) {
        RealizaMatch realizaMatchExistente = realizaMatchRepository.findById(id).orElse(null);
        if (realizaMatchExistente != null){
            realizaMatchExistente.setMatchMascota(realizaMatchEditado.getMatchMascota());
            realizaMatchExistente.setPorcentajeAfinidad(realizaMatchEditado.getPorcentajeAfinidad());

            realizaMatchRepository.save(realizaMatchExistente);
        } else {
            throw new RuntimeException("Realiza match no encontrado con el Id " + id);
        }

    }

    @Override
    public List<RealizaMatch> posibleMatch(Long id) {
        return realizaMatchRepository.findMatchPendientesByUsuarioId(id);
    }

    @Override
    public void like(Long id, Boolean reaccion) {
        RealizaMatch realizaMatchExistente = realizaMatchRepository.findById(id).orElse(null);
        if (realizaMatchExistente != null){
            realizaMatchExistente.setMatchMascota(reaccion);

            realizaMatchRepository.save(realizaMatchExistente);
        } else {
            throw new RuntimeException("Realiza match no encontrado con el Id " + id);
        }
    }

    @Override
    public List<RealizaMatch> buscarMatches(Long id) {
        return realizaMatchRepository.findMatchByUsuarioId(id);
    }
}
