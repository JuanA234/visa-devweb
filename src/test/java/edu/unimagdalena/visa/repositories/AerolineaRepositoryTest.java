package edu.unimagdalena.visa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AerolineaRepositoryTest {

    @Container
    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.4-alpine").
            ;

    @Test
    void findById() {
    }

    @Test
    void findByNombre() {
    }

    @Test
    void findByNombreStartingWith() {
    }

    @Test
    void findByNombreEndingWith() {
    }

    @Test
    void findByNombreContaining() {
    }

    @Test
    void findAerolineasConNombres() {
    }

    @Test
    void findAllAerolineasOrdenadas() {
    }

    @Test
    void findAerolineasConVuelos() {
    }

    @Test
    void contarVuelosPorAerolinea() {
    }

    @Test
    void findAerolineasSinVuelos() {
    }
}