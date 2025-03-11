package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Aerolinea;
import edu.unimagdalena.visa.entities.Pasajero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasajeroRepositoryTest {

    @Autowired
    PasajeroRepository pasajeroRepository;

    @Test
    void findByNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("PasajeroTest").build();
        pasajeroRepository.save(pasajero);
        List<Pasajero> resultado = pasajeroRepository.findByNombre("PasajeroTest");
        assertEquals(1, resultado.size());
        assertEquals(pasajero.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    void findByNid() {
        Pasajero pasajero = Pasajero.builder().nombre("Sarmiento").build();
        pasajeroRepository.save(pasajero);
        Optional<Pasajero> pasajeroEncontrado = pasajeroRepository.findByNid(pasajero.getNid());
        assertTrue(pasajeroEncontrado.isPresent());
        assertEquals(pasajero.getNombre(), pasajeroEncontrado.get().getNombre());
    }

    @Test
    void findByNidAndNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("Sarmiento").build();
        pasajeroRepository.save(pasajero);
        Optional<Pasajero> pasajeroEncontrado = pasajeroRepository.findByNidAndNombre(pasajero.getNid(), pasajero.getNombre());
        assertTrue(pasajeroEncontrado.isPresent());
    }

    @Test
    void findPasajeroById() {
        Pasajero pasajero = Pasajero.builder().nombre("Sarmiento").build();
        pasajeroRepository.save(pasajero);
        Optional<Pasajero> pasajeroEncontrado = pasajeroRepository.findPasajeroById(pasajero.getId());
        assertTrue(pasajeroEncontrado.isPresent());
        assertEquals(pasajero.getNombre(), pasajeroEncontrado.get().getNombre());
    }

    @Test
    void findDistinctByNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("Unico").build();
        pasajeroRepository.save(pasajero);
        Optional<Pasajero> resultado = pasajeroRepository.findDistinctByNombre("Unico");
        assertTrue(resultado.isPresent());
        assertEquals("Unico", resultado.get().getNombre());
    }

    @Test
    void findPasajerosConNombres() {
    }

    @Test
    void findByNombreStartingWith() {
    }

    @Test
    void countPasajerosWithPasaporte() {
    }

    @Test
    void findPasajerosWithoutReservas() {
    }

    @Test
    void findByNombreContainingIgnoreCase() {
    }
}