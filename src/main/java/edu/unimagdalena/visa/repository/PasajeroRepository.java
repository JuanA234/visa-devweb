package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    Optional<Pasajero> findPasajeroById(Long id);
    List<Pasajero> findByNombre(String nombre);
    Optional<Pasajero> findByNid(String nid);
    Optional<Pasajero> findByNidAndNombre(String nid, String nombre);
    Optional<Pasajero> findDistinctByNombre(String nombre);

    @Query("select p from Pasajero p where p.nombre in (?1, ?2)")
    List<Pasajero> findPasajerosConNombres(String nombre1, String nombre2);

    @Query("SELECT p FROM Pasajero p WHERE p.nombre LIKE ?1%")
    List<Pasajero> findByNombreStartingWith(String prefix);

    // Contar cuántos pasajeros tienen un pasaporte asociado
    @Query("SELECT COUNT(p) FROM Pasajero p WHERE p.pasaporte IS NOT NULL")
    Long countPasajerosWithPasaporte();

    @Query("SELECT p FROM Pasajero p WHERE p.reservas IS EMPTY")
    List<Pasajero> findPasajerosWithoutReservas();

    // Buscar pasajeros por un fragmento del nombre (insensible a mayúsculas/minúsculas)
    @Query("SELECT p FROM Pasajero p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Pasajero> findByNombreContainingIgnoreCase(String fragment);


}
