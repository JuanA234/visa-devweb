package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Reserva;
import edu.unimagdalena.visa.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findVueloById(Long id);
    List<Vuelo> findByNumeroStartingWith(String prefix);
    List<Vuelo> findByNumeroVuelo(UUID numeroVuelo);

    Optional<Vuelo> findByReservasEmpty(Set<Reserva> reservas);
    Optional<Vuelo> findByReservas(Set<Reserva> reservas);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = ?1")
    List<Vuelo> findByOrigen(String origen);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1")
    List<Vuelo> findByDestino(String destino);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1 AND v.origen = ?2")
    List<Vuelo> findByDestinoAndOrigen(String destino, String origen);

     @Query("SELECT v FROM Vuelo v WHERE v.destino in (?1, ?2, ?3)")
     List<Vuelo> findByDestinoIn(Set<String> destinos);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1 OR v.origen = ?1")
    List<Vuelo> findByDestinoOrOrigen(String location);

}
