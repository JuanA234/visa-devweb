package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    //Query methods
    Optional<Aerolinea> findById(Long id);

    List<Aerolinea> findByNombre(String nombre);

    List<Aerolinea> findByNombreStartingWith(String inicioNombre);

    List<Aerolinea> findByNombreContaining(String parteNombre);

    //Query JPQL
    @Query("select distinct a from Aerolinea a join a.vuelos v where v.origen = ?1")
    List<Aerolinea> findAerolineasWithVueloOrigen(String origen);

    @Query("select distinct a from Aerolinea a join a.vuelos v where v.destino = ?1")
    List<Aerolinea> findAerolineasWithVueloDestino(String destino);

    @Query("select count(v) from Aerolinea a join a.vuelos v where a.id = ?1")
    Long countVuelosByAerolinea(Long id);




}