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

import java.util.List;
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
        Optional<Aerolinea> aerolineaEncontrado = aerolineaRepository.findById(aerolinea.getId());
        assertTrue(aerolineaEncontrado.isPresent());
        assertEquals(aerolinea.getNombre(), aerolineaEncontrado.get().getNombre());
    }

    @Test
    void findByNombre() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("AeroTest").build();
        aerolineaRepository.save(aerolinea);
        List<Aerolinea> resultado = aerolineaRepository.findByNombre("AeroTest");
        assertEquals(1, resultado.size());
        assertEquals(aerolinea.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    void findByNombreStartingWith() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("AeroStart").build();
        aerolineaRepository.save(aerolinea);
        List<Aerolinea> resultados = aerolineaRepository.findByNombreStartingWith("Aero");
        assertFalse(resultados.isEmpty());
        assertEquals("AeroStart", resultados.get(0).getNombre());
    }

    @Test
    void findByNombreEndingWith() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("EndTest").build();
        aerolineaRepository.save(aerolinea);
        List<Aerolinea> resultados = aerolineaRepository.findByNombreEndingWith("Test");
        assertFalse(resultados.isEmpty());
        assertEquals("EndTest", resultados.get(0).getNombre());
    }

    @Test
    void findByNombreContaining() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("MiddleTest").build();
        aerolineaRepository.save(aerolinea);
        List<Aerolinea> resultados = aerolineaRepository.findByNombreContaining("ddle");
        assertFalse(resultados.isEmpty());
        assertEquals("MiddleTest", resultados.get(0).getNombre());
    }

    @Test
    void findAerolineasConNombres() {
        Aerolinea aerolinea1 = aerolineaRepository.save(Aerolinea.builder().nombre("AeroOne").build());
        Aerolinea aerolinea2 = aerolineaRepository.save(Aerolinea.builder().nombre("AeroTwo").build());
        List<Aerolinea> resultados = aerolineaRepository.findAerolineasConNombres(aerolinea1.getNombre(), aerolinea2.getNombre());
        assertEquals(2, resultados.size());
    }

    @Test
    void findAllAerolineasOrdenadas() {
        aerolineaRepository.save(Aerolinea.builder().nombre("Beta").build());
        aerolineaRepository.save(Aerolinea.builder().nombre("Alpha").build());
        List<Aerolinea> resultados = aerolineaRepository.findAllAerolineasOrdenadas();
        assertEquals("Alpha", resultados.get(0).getNombre());
    }

    @Test
    void findAerolineasConVuelos() {
        List<Aerolinea> resultados = aerolineaRepository.findAerolineasConVuelos();
        assertNotNull(resultados);
    }

    @Test
    void contarVuelosPorAerolinea() {
        Long count = aerolineaRepository.contarVuelosPorAerolinea(1L);
        assertNotNull(count);
    }

    @Test
    void findAerolineasSinVuelos() {
        List<Aerolinea> resultados = aerolineaRepository.findAerolineasSinVuelos();
        assertNotNull(resultados);
    }
}