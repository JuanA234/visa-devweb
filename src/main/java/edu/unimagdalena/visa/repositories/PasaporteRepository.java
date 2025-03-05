package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
    List<Pasaporte> findPasaporteByNombreContainingIgnoreCase(String nombre);
    List<Pasaporte> findDistinctByNombre(String nombre);
    Optional<Pasaporte> findPasaporteById(Long id);
    Optional<Pasaporte> findPasaporteByNumero(String numero);
    List<Pasaporte> findPasaportesByIdOrNombre(Long id, String nombre);

    // Buscar pasaportes cuyo número contenga una determinada secuencia de caracteres
    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE %?1%")
    List<Pasaporte> findByNumeroContaining(String fragment);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> findPasaportesWithoutPasajero();

    @Query("SELECT COUNT(p) FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    long countPasaportesWithPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE ?1%")
    List<Pasaporte> findByNumeroStartingWith(String prefix);
}
