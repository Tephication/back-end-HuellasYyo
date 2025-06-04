package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.ConvivenciaPreferencia;

import com.example.HuellasYyo.repository.IConvivenciaPreferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvivenciaPreferenciaService implements IConvivenciaPreferenciaService{
    private final IConvivenciaPreferenciaRepository convivenciaPreferenciaRepository;

    @Autowired
    public ConvivenciaPreferenciaService(IConvivenciaPreferenciaRepository convivenciaPreferenciaRepository) {
        this.convivenciaPreferenciaRepository = convivenciaPreferenciaRepository;
    }

    @Override
    public List<ConvivenciaPreferencia> obtenerDatos() {
        return convivenciaPreferenciaRepository.findAll();
    }

    @Override
    public ConvivenciaPreferencia encontrarPorId(Long id) {
        return convivenciaPreferenciaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ConvivenciaPreferencia convivenciaPreferenciaGuardado) {
        convivenciaPreferenciaRepository.save(convivenciaPreferenciaGuardado);

    }

    @Override
    public void deleteConvivenciaPreferencia(Long id) {
        convivenciaPreferenciaRepository.deleteById(id);
    }

    @Override
    public void editarConvivenciaPreferencia(Long id, ConvivenciaPreferencia convivenciaPreferenciaEditado) {
        ConvivenciaPreferencia convivenciaPreferenciaExistente = convivenciaPreferenciaRepository.findById(id).orElse(null);
        if (convivenciaPreferenciaExistente != null){
            convivenciaPreferenciaExistente.setExperiencia(convivenciaPreferenciaEditado.isExperiencia());
            convivenciaPreferenciaExistente.setDedicacionTiempo(convivenciaPreferenciaEditado.getDedicacionTiempo());
            convivenciaPreferenciaExistente.setCompatibilidadNinos(convivenciaPreferenciaEditado.isCompatibilidadNinos());
            convivenciaPreferenciaExistente.setCompatibilidadMascotas(convivenciaPreferenciaEditado.isCompatibilidadMascotas());
            convivenciaPreferenciaExistente.setTipoVivienda(convivenciaPreferenciaEditado.getTipoVivienda());
            convivenciaPreferenciaExistente.setAdiestramientoOfrecido(convivenciaPreferenciaEditado.getAdiestramientoOfrecido());
            convivenciaPreferenciaExistente.setViajesEnAuto(convivenciaPreferenciaEditado.isViajesEnAuto());


            convivenciaPreferenciaRepository.save(convivenciaPreferenciaExistente);
        } else {
            throw new RuntimeException("Preferencia de convivencia no encontrada con el Id " + id);
        }

    }
}
