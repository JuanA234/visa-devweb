package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.model.Reserva;
import edu.unimagdalena.visa.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    //Query methods
    Optional<Reserva> findById(Long id);

    Optional<Reserva> findByCodigoReserva(UUID codigoReserva);

    List<Reserva> findByPasajero(Pasajero pasajero);

    List<Reserva> findByVuelo(Vuelo vuelo);

    Long countByVuelo(Vuelo vuelo);

    //Query JPQL
    @Query("select r from Reserva r where r.pasajero.id = ?1")
    List<Reserva> findReservasByPasajeroId(Long pasajeroId);

    @Query("select r from Reserva r where r.vuelo.id = ?1")
    List<Reserva> findReservasByVueloId(Long vueloId);

    @Query("select r from Reserva r order by r.codigoReserva asc")
    List<Reserva> findAllReservasOrdenadasPorCodigo();

    @Query("select count(r) from Reserva r")
    Long countTotalReservas();

    @Query("select r from Reserva r where r.pasajero is null")
    List<Reserva> findReservasSinPasajero();
}
