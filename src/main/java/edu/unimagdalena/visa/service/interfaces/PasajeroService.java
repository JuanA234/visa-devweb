package edu.unimagdalena.visa.service.interfaces;

import edu.unimagdalena.visa.dto.Pasajero.RequestPasajeroDTO;
import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.model.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    ResponsePasajeroDTO createPasajero(RequestPasajeroDTO dto);

    Optional<ResponsePasajeroDTO> getPasajeroById(Long id);

    List<ResponsePasajeroDTO> getPasajerosByNombre(String nombre);

    Optional<ResponsePasajeroDTO> getPasajeroByNid(String nid);

    Optional<ResponsePasajeroDTO> getPasajeroByNidAndNombre(String nid, String nombre);

    Optional<ResponsePasajeroDTO> getPasajeroDistintByNombre(String nombre);

    List<ResponsePasajeroDTO> getPasajerosConNombres(String nombre1, String nombre2);

    List<ResponsePasajeroDTO> getPasajeroByNombreStartingWith(String prefix);

    Long countPasajerosWithPasaporte();

    List<ResponsePasajeroDTO> getPasajerosWithoutReservas();

    List<ResponsePasajeroDTO> getPasajeroByNombreContainingIgnoreCase(String fragment);
}
