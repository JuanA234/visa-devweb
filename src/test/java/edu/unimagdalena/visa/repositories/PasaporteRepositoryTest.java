package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Pasaporte;
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
class PasaporteRepositoryTest {

    @Autowired
    PasaporteRepository pasaporteRepository;

    @Test
    void findDistinctByNumero() {
        Pasaporte pasaporte = Pasaporte.builder().numero("ABC123").build();
        pasaporteRepository.save(pasaporte);
        List<Pasaporte> resultado = pasaporteRepository.findDistinctByNumero("ABC123");
        assertFalse(resultado.isEmpty());
        assertEquals("ABC123", resultado.get(0).getNumero());
    }

    @Test
    void findPasaporteById() {
        Pasaporte pasaporte = Pasaporte.builder().numero("DEF456").build();
        pasaporteRepository.save(pasaporte);
        Optional<Pasaporte> resultado = pasaporteRepository.findPasaporteById(pasaporte.getId());
        assertTrue(resultado.isPresent());
        assertEquals("DEF456", resultado.get().getNumero());
    }

    @Test
    void findPasaportesByIdOrNumero() {
        Pasaporte pasaporte1 = Pasaporte.builder().numero("ABC123").build();
        Pasaporte pasaporte2 = Pasaporte.builder().numero("DEF456").build();
        pasaporteRepository.save(pasaporte1);
        pasaporteRepository.save(pasaporte2);
        List<Pasaporte> resultado = pasaporteRepository.findPasaportesByIdOrNumero(pasaporte1.getId(), pasaporte2.getNumero());
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }

    @Test
    void findAllByNumeroStartingWith() {
        Pasaporte pasaporte1 = Pasaporte.builder().numero("ABC123").build();
        Pasaporte pasaporte2 = Pasaporte.builder().numero("ABC456").build();
        pasaporteRepository.save(pasaporte1);
        pasaporteRepository.save(pasaporte2);
        List<Pasaporte> resultado = pasaporteRepository.findAllByNumeroStartingWith("ABC");
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertEquals("ABC123", resultado.get(0).getNumero());
        assertEquals("ABC456", resultado.get(1).getNumero());
    }

    @Test
    void findAllByNumeroContaining() {
        pasaporteRepository.save(Pasaporte.builder().numero("LMN456").build());
        pasaporteRepository.save(Pasaporte.builder().numero("DMN789").build());
        List<Pasaporte> resultados = pasaporteRepository.findAllByNumeroContaining("MN");
        assertFalse(resultados.isEmpty());
        assertEquals(2, resultados.size());
    }

    @Test
    void findByNumeroContaining() {
        pasaporteRepository.save(Pasaporte.builder().numero("OPQ789").build());
        List<Pasaporte> resultados = pasaporteRepository.findByNumeroContaining("PQ");
        assertFalse(resultados.isEmpty());
    }

    @Test
    void findPasaportesWithoutPasajero() {
        List<Pasaporte> resultados = pasaporteRepository.findPasaportesWithoutPasajero();
        assertNotNull(resultados);
    }

    @Test
    void countPasaportesWithPasajero() {
        pasaporteRepository.save(Pasaporte.builder().numero("RST012").build());
        long count = pasaporteRepository.countPasaportesWithPasajero();
        assertTrue(count >= 0);
    }

    @Test
    void findByNumeroStartingWith() {
        pasaporteRepository.save(Pasaporte.builder().numero("UVW345").build());
        List<Pasaporte> resultados = pasaporteRepository.findByNumeroStartingWith("UVW");
        assertFalse(resultados.isEmpty());
    }

    @Test
    void findPasaportesWithPasajero() {
        List<Pasaporte> resultados = pasaporteRepository.findPasaportesWithPasajero();
        assertNotNull(resultados);
    }
}