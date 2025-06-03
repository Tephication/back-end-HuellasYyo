package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.ProcesoAdopcion;
import com.example.HuellasYyo.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService implements IMascotaService{
    private final IMascotaRepository mascotaRepository;

    @Autowired
    public MascotaService(IMascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<Mascota> obtenerDatos() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota encontrarPorId(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Mascota mascotaGuardado) {
        mascotaRepository.save(mascotaGuardado);
    }

    @Override
    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public void editarMascota(Long id, Mascota mascotaEditado) {
        Mascota mascotaExistente = mascotaRepository.findById(id).orElse(null);
        if (mascotaExistente != null){
            mascotaExistente.setNombre(mascotaEditado.getNombre());
            mascotaExistente.setEspecie(mascotaEditado.getEspecie());
            mascotaExistente.setSexo(mascotaEditado.getSexo());
            mascotaExistente.setCaracter(mascotaEditado.getCaracter());
            mascotaExistente.setEdad(mascotaEditado.getEdad());
            mascotaExistente.setTamano(mascotaEditado.getTamano());
            mascotaExistente.setRaza(mascotaEditado.getRaza());
            mascotaExistente.setUrlImagenMascota(mascotaEditado.getUrlImagenMascota());
            mascotaExistente.setDisponibilidad(mascotaEditado.isDisponibilidad());
            mascotaExistente.setOtrasCaracteristicas(mascotaEditado.getOtrasCaracteristicas());

            mascotaRepository.save(mascotaExistente);
        } else {
            throw new RuntimeException("Mascota no encontrada con el Id " + id);
        }

    }
}
