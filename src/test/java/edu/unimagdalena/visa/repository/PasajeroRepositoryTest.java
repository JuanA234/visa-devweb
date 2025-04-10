package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.model.Pasaporte;
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

    @Autowired PasajeroRepository pasajeroRepository;
    @Autowired PasaporteRepository pasaporteRepository;

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
        Pasajero pasajero1 = Pasajero.builder().nombre("Carlos").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Ana").build();
        pasajeroRepository.save(pasajero1);
        pasajeroRepository.save(pasajero2);

        List<Pasajero> resultado = pasajeroRepository.findPasajerosConNombres("Carlos", "Ana");
        assertEquals(2, resultado.size());
    }

    @Test
    void findByNombreStartingWith() {
        Pasajero pasajero = Pasajero.builder().nombre("Juan").build();
        pasajeroRepository.save(pasajero);

        List<Pasajero> resultado = pasajeroRepository.findByNombreStartingWith("Ju");
        assertFalse(resultado.isEmpty());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void countPasajerosWithPasaporte() {
        Pasaporte pasaporte = Pasaporte.builder().build();
        pasaporte = pasaporteRepository.save(pasaporte);

        Pasajero pasajero1 = Pasajero.builder().nombre("Carlos").pasaporte(pasaporte).build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Ana").build();
        pasajeroRepository.save(pasajero1);
        pasajeroRepository.save(pasajero2);

        Long count = pasajeroRepository.countPasajerosWithPasaporte();
        assertEquals(1, count);
    }

    @Test
    void findPasajerosWithoutReservas() {
        Pasajero pasajero = Pasajero.builder().nombre("Luis").build();
        pasajeroRepository.save(pasajero);

        List<Pasajero> resultado = pasajeroRepository.findPasajerosWithoutReservas();
        assertFalse(resultado.isEmpty());
        assertEquals("Luis", resultado.get(0).getNombre());
    }

    @Test
    void findByNombreContainingIgnoreCase() {
        Pasajero pasajero = Pasajero.builder().nombre("Gabriela").build();
        pasajeroRepository.save(pasajero);

        List<Pasajero> resultado = pasajeroRepository.findByNombreContainingIgnoreCase("ga");
        assertFalse(resultado.isEmpty());
        assertEquals("Gabriela", resultado.get(0).getNombre());
    }
}