package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    Optional<Aerolinea> findById(Long id);
    List<Aerolinea> findByNombre(String nombre);
    List<Aerolinea> findByNombreStartingWith(String inicioNombre);
    List<Aerolinea> findByNombreEndingWith(String finNombre);
    List<Aerolinea> findByNombreContaining(String parteNombre);

    @Query("select a from Aerolinea a where a.nombre in (?1, ?2)")
    List<Aerolinea> findAerolineasConNombres(String aerolinea1, String aerolinea2);


}