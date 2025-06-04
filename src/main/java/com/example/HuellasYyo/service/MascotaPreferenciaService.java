package com.example.HuellasYyo.service;


import com.example.HuellasYyo.model.MascotaPreferencia;
import com.example.HuellasYyo.repository.IMascotaPreferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaPreferenciaService implements IMascotaPreferenciaService{

    private final IMascotaPreferenciaRepository mascotaPreferenciaRepository;

    @Autowired
    public MascotaPreferenciaService(IMascotaPreferenciaRepository mascotaPreferenciaRepository) {
        this.mascotaPreferenciaRepository = mascotaPreferenciaRepository;
    }

    @Override
    public List<MascotaPreferencia> obtenerDatos() {
        return mascotaPreferenciaRepository.findAll();
    }

    @Override
    public MascotaPreferencia encontrarPorId(Long id) {
        return mascotaPreferenciaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MascotaPreferencia mascotaPreferenciaGuardado) {
        mascotaPreferenciaRepository.save(mascotaPreferenciaGuardado);
    }

    @Override
    public void deletMascotaPreferencia(Long id) {
        mascotaPreferenciaRepository.deleteById(id);

    }

    @Override
    public void editarMascotaPreferencia(Long id, MascotaPreferencia mascotaPreferenciaEditado) {
        MascotaPreferencia mascotaPreferenciaExistente = mascotaPreferenciaRepository.findById(id).orElse(null);
        if (mascotaPreferenciaExistente != null){
            mascotaPreferenciaExistente.setCaracterPreferencia(mascotaPreferenciaEditado.getCaracterPreferencia());
            mascotaPreferenciaExistente.setSexoPreferencia(mascotaPreferenciaEditado.getSexoPreferencia());
            mascotaPreferenciaExistente.setEspecieBuscada(mascotaPreferenciaEditado.getEspecieBuscada());
            mascotaPreferenciaExistente.setEdadPreferencia(mascotaPreferenciaEditado.getEdadPreferencia());
            mascotaPreferenciaExistente.setTamanoPreferencia(mascotaPreferenciaEditado.getTamanoPreferencia());


            mascotaPreferenciaRepository.save(mascotaPreferenciaExistente);
        } else {
            throw new RuntimeException("Preferencia de mascota no encontrada con el Id " + id);
        }

    }
}
