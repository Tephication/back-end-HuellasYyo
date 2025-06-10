package com.example.HuellasYyo.service;

import com.example.HuellasYyo.model.Mascota;
import com.example.HuellasYyo.model.MascotaPreferencia;
import org.springframework.stereotype.Service;

@Service
public class ComparadorService implements IComparadorService {

    public ComparadorService() {
    }

    @Override
    public double calcularCompatibilidad(Mascota mascota, MascotaPreferencia preferencia) {
        double compatibilidad = 0.0;

        // 1. Especie: 25%
        if (compararEspecie(mascota.getEspecie(), preferencia.getEspecieBuscada())) {
            compatibilidad += 25.0;
        }

        // 2. Tamaño: 25%
        if (compararTamano(mascota.getTamano(), preferencia.getTamanoPreferencia())) {
            compatibilidad += 25.0;
        }

        // 3. Carácter: 30%
        if (compararCaracter(mascota.getCaracter(), preferencia.getCaracterPreferencia())) {
            compatibilidad += 30.0;
        }

        // 4. Edad: 20%
        if (compararEdad(Integer.parseInt(mascota.getEdad()), preferencia.getEdadPreferencia())) {
            compatibilidad += 20.0;
        }

        return compatibilidad; // Máximo: 100.0
    }

    private boolean compararEspecie(String especieMascota, String preferenciaEspecie) {
        if (preferenciaEspecie.equalsIgnoreCase("Cualquiera")) return true;

        String especie = especieMascota.trim().toLowerCase();
        String preferencia = preferenciaEspecie.trim().toLowerCase();

        return (especie.equals("gato") && preferencia.equals("gato(a)"))
                || (especie.equals("perro") && preferencia.equals("perro(a)"));
    }


    private boolean compararTamano(String tamanoMascota, String preferenciaTamano) {
        if (preferenciaTamano.equalsIgnoreCase("N/A")) return true;

        String mascota = tamanoMascota.trim().toLowerCase();
        String preferencia = preferenciaTamano.trim().toLowerCase();

        return mascota.equals(preferencia);
    }


    private boolean compararCaracter(String caracterMascota, String preferenciaCaracter) {
        if (preferenciaCaracter.equalsIgnoreCase("Activo")) {
            return caracterMascota.equalsIgnoreCase("Activo") || caracterMascota.equalsIgnoreCase("Juguetón");
        } else if (preferenciaCaracter.equalsIgnoreCase("Pasivo")) {
            return caracterMascota.equalsIgnoreCase("Pasivo");
        }
        return false;
    }

    private boolean compararEdad(int edadMascota, String preferenciaEdad) {
        return switch (preferenciaEdad) {
            case "Cualquiera" -> true; // No importa la edad
            case "Cachorro(a)" -> edadMascota <= 1;
            case "Joven" -> edadMascota >= 2 && edadMascota <= 5;
            case "Adulto(a)" -> edadMascota >= 6;
            default -> false;
        };
    }


}
