package edu.unimagdalena.visa.services.interfaces;

import edu.unimagdalena.visa.dto.PasajeroDTO;
import edu.unimagdalena.visa.entities.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    Pasajero createPasajero(Pasajero pasajero);

    Optional<PasajeroDTO> getPasajeroById(Long id);

    List<Pasajero> getPasajerosByNombre(String nombre);

    Optional<Pasajero> getPasajeroByNid(String nid);

    Optional<Pasajero> getPasajeroByNidAndNombre(String nid, String nombre);

    Optional<Pasajero> getPasajeroDistintByNombre(String nombre);

    List<Pasajero> getPasajerosConNombres(String nombre1, String nombre2);

    List<Pasajero> getPasajeroByNombreStartingWith(String prefix);

    Long countPasajerosWithPasaporte();

    List<Pasajero> getPasajerosWithoutReservas();

    List<Pasajero> getPasajeroByNombreContainingIgnoreCase(String fragment);
}
