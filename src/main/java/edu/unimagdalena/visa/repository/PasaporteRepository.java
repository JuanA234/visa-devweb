package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
    List<Pasaporte> findDistinctByNumero(String numero);
    Optional<Pasaporte> findPasaporteById(Long id);
    List<Pasaporte> findPasaportesByIdOrNumero(Long id, String numero);
    List<Pasaporte> findAllByNumeroStartingWith(String prefix);
    List<Pasaporte> findAllByNumeroContaining(String fragment);


    // Buscar pasaportes cuyo número contenga una determinada secuencia de caracteres
    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE %?1%")
    List<Pasaporte> findByNumeroContaining(String fragment);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> findPasaportesWithoutPasajero();

    @Query("SELECT COUNT(p) FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    long countPasaportesWithPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE ?1%")
    List<Pasaporte> findByNumeroStartingWith(String prefix);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    List<Pasaporte> findPasaportesWithPasajero();
}
