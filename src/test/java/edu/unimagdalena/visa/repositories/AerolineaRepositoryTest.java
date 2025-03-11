package edu.unimagdalena.visa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
public class AerolineaRepositoryTest {

    @Container
    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.4-alpine")
            .withDatabaseName("visa")
            .withUsername("postgres")
            .withPassword("1234");

    @Test
    void findById() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
        assertEquals("1234", postgreSQLContainer.getPassword());
    }

    @Test
    void findByNombre() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findByNombreStartingWith() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findByNombreEndingWith() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findByNombreContaining() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findAerolineasConNombres() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findAllAerolineasOrdenadas() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findAerolineasConVuelos() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void contarVuelosPorAerolinea() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }

    @Test
    void findAerolineasSinVuelos() {
        assertEquals(1, postgreSQLContainer.getFirstMappedPort());
        assertEquals("Aerolinea 1", postgreSQLContainer.getDatabaseName());
        assertEquals("postgres", postgreSQLContainer.getUsername());
    }
}