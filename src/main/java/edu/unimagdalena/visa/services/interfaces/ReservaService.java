package edu.unimagdalena.visa.services.interfaces;

import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.entities.Reserva;
import edu.unimagdalena.visa.entities.Vuelo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaService {
    //Cambiar por DTO en futuras versiones
    Reserva createReserva(Reserva reserva);
    Optional<Reserva> getReservaById(Long id);
    Optional<Reserva> getReservaByCodigo(UUID codigoReserva);
    List<Reserva> getReservasByPasajero(Pasajero pasajero);
    List<Reserva> getReservasByVuelo(Vuelo vuelo);
    Long countReservasByVuelo(Vuelo vuelo);
}
