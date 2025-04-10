package edu.unimagdalena.visa.service.interfaces;

import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.model.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    Pasajero createPasajero(Pasajero pasajero);

    Optional<ResponsePasajeroDTO> getPasajeroById(Long id);

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
