package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.ProcesoAdopcion;
import com.example.HuellasYyo.model.RealizaMatch;
import com.example.HuellasYyo.repository.IProcesoAdopcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoAdopcionService implements IProcesoAdopcionService{
    private final IProcesoAdopcionRepository procesoAdopcionRepository;

    @Autowired
    public ProcesoAdopcionService(IProcesoAdopcionRepository procesoAdopcionRepository) {
        this.procesoAdopcionRepository = procesoAdopcionRepository;
    }

    @Override
    public List<ProcesoAdopcion> obtenerDatos() {
        return procesoAdopcionRepository.findAll() ;
    }

    @Override
    public ProcesoAdopcion encontrarPorId(Long id) {
        return procesoAdopcionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProcesoAdopcion procesoAdopcionGuardado) {
        procesoAdopcionRepository.save(procesoAdopcionGuardado);

    }

    @Override
    public void deleteProcesoAdopcion(Long id) {
        procesoAdopcionRepository.deleteById(id);

    }

    @Override
    public void editarProcesoAdopcion(Long id, ProcesoAdopcion procesoAdopcionEditado) {
        ProcesoAdopcion procesoAdopcionExistente = procesoAdopcionRepository.findById(id).orElse(null);
        if (procesoAdopcionExistente != null){
            procesoAdopcionExistente.setEstado(procesoAdopcionEditado.getEstado());
            procesoAdopcionExistente.setUrlCertificadoIngresos(procesoAdopcionEditado.getUrlCertificadoIngresos());
            procesoAdopcionExistente.setUrlFormAdopcion(procesoAdopcionEditado.getUrlFormAdopcion());
            procesoAdopcionExistente.setUrlVideo(procesoAdopcionEditado.getUrlVideo());
            procesoAdopcionExistente.setUrlCedula(procesoAdopcionEditado.getUrlCedula());
            procesoAdopcionExistente.setUrlAntecedentesPenales(procesoAdopcionEditado.getUrlAntecedentesPenales());
            procesoAdopcionExistente.setFkIdUsuario(procesoAdopcionEditado.getFkIdUsuario());
            procesoAdopcionExistente.setFkIdMascota(procesoAdopcionEditado.getFkIdMascota());

            procesoAdopcionRepository.save(procesoAdopcionExistente);
        } else {
            throw new RuntimeException("Proceso de adopción no encontrado con el Id " + id);
        }

    }
}
