package edu.unimagdalena.visa.service.interfaces;

import edu.unimagdalena.visa.model.Vuelo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VueloService {
    //Cambiar por DTO en futuras versiones
    Vuelo createVuelo(Vuelo vuelo);

    Optional<Vuelo> getVueloById(Long id);

    List<Vuelo> getAllByOrderByOrigen();

    Optional<Vuelo> getVueloByNumero(UUID numeroVuelo);

    List<Vuelo> getVuelosByOrigenStartingWith(String prefix);

    List<Vuelo> getVuelosByDestinoStartingWith(String prefix);
}