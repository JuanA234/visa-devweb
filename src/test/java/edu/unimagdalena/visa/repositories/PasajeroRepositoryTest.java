package edu.unimagdalena.visa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

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

    }

    @Test
    void findByNid() {
    }

    @Test
    void findByNidAndNombre() {
    }

    @Test
    void findPasajeroById() {
    }

    @Test
    void findDistinctByNombre() {
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