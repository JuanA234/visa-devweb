package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Aerolinea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AerolineaRepositoryTest {

//    @Container
//    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.4-alpine");

    @Autowired
    AerolineaRepository aerolineaRepository;

    @Test
    void findById() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("Aerolinea onichan").build();
        aerolineaRepository.save(aerolinea);
        Optional<Aerolinea> aerolineaEncontrado = aerolineaRepository.findById(1L);
        assertTrue(aerolineaEncontrado.isPresent());
        assertEquals(aerolinea.getNombre(), aerolineaEncontrado.get().getNombre());
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