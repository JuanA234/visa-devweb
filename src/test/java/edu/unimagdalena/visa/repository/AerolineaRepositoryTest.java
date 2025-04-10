package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Aerolinea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AerolineaRepositoryTest {
    @Autowired
    AerolineaRepository aerolineaRepository;

    @Test
    void findById() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("Aerolinea onichan").vuelos(Set.of()).build();
        aerolineaRepository.save(aerolinea);
        Optional<Aerolinea> aerolineaEncontrado = aerolineaRepository.findById(aerolinea.getId());

        assertNotNull(aerolineaEncontrado.get().getVuelos());
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

//    @Test
//    void findByNombreEndingWith() {
//        Aerolinea aerolinea = Aerolinea.builder().nombre("EndTest").build();
//        aerolineaRepository.save(aerolinea);
//        List<Aerolinea> resultados = aerolineaRepository.findByNombreEndingWith("Test");
//        assertFalse(resultados.isEmpty());
//        assertEquals("EndTest", resultados.get(0).getNombre());
//    }

    @Test
    void findByNombreContaining() {
        Aerolinea aerolinea = Aerolinea.builder().nombre("MiddleTest").build();
        aerolineaRepository.save(aerolinea);
        List<Aerolinea> resultados = aerolineaRepository.findByNombreContaining("ddle");
        assertFalse(resultados.isEmpty());
        assertEquals("MiddleTest", resultados.get(0).getNombre());
    }

    @Test
    void contarVuelosPorAerolinea() {
        Long count = aerolineaRepository.countVuelosByAerolinea(1L);
        assertNotNull(count);
    }

    @Test
    void findAerolineasWithVueloOrigen() {
    }

    @Test
    void findAerolineasWithVueloDestino() {
    }
}