package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findByNombre(String nombre);
    Optional<Pasajero> findByNid(String nid);
    Optional<Pasajero> findByNidAndNombre(String nid, String nombre);
    Optional<Pasajero> findById(Long id);
    Optional<Pasajero> findDistinctByNombre(String nombre);

    @Query("select p from Pasajero p where p.nombre in (?1, ?2)")
    List<Pasajero> findPasajerosConNombres(String nombre1, String nombre2);

    @Query("SELECT p FROM Pasajero p WHERE p.nombre LIKE ?1%")
    List<Pasajero> findByNombreStartingWith(String prefix);

    // Contar cuántos pasajeros tienen un pasaporte asociado
    @Query("SELECT COUNT(p) FROM Pasajero p WHERE p.pasaporte IS NOT NULL")
    long countPasajerosWithPasaporte();

    @Query("SELECT p FROM Pasajero p WHERE p.reservas IS EMPTY")
    List<Pasajero> findPasajerosWithoutReservas();

    // Buscar pasajeros por un fragmento del nombre (insensible a mayúsculas/minúsculas)
    @Query("SELECT p FROM Pasajero p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Pasajero> findByNombreContainingIgnoreCase(String fragment);


}
