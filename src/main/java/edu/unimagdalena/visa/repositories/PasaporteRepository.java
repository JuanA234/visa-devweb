package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
    List<Pasaporte> findPasaporteByNombreContainingIgnoreCase(String nombre);
    List<Pasaporte> findDistinctByNombre(String nombre);
    Optional<Pasaporte> findPasaporteById(Long id);
    Optional<Pasaporte> findPasaporteByNumero(String numero);
    List<Pasaporte> findPasaportesByIdOrNombre(Long id, String nombre);


}
