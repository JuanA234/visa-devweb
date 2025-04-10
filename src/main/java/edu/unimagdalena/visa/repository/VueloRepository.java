package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    Optional<Vuelo> findVueloById(Long id);
    List<Vuelo> findAllByOrderByOrigenAsc();
    Optional<Vuelo> findByNumeroVuelo(UUID numeroVuelo);
    List<Vuelo> findByOrigenStartingWith(String prefix);
    List<Vuelo> findByDestinoStartingWith(String prefix);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = ?1")
    List<Vuelo> findByOrigen(String origen);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1")
    List<Vuelo> findByDestino(String destino);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1 AND v.origen = ?2")
    List<Vuelo> findByDestinoAndOrigen(String destino, String origen);

     @Query("SELECT v FROM Vuelo v WHERE v.destino in (?1, ?2, ?3)")
     List<Vuelo> findByDestinoIn(String uno, String dos, String tres);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1 OR v.origen = ?1")
    List<Vuelo> findByDestinoOrOrigen(String location);

}
