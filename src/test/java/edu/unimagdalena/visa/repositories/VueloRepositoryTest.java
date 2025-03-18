package edu.unimagdalena.visa.repositories;

import edu.unimagdalena.visa.entities.Vuelo;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VueloRepositoryTest {
    @Autowired VueloRepository vueloRepository;

    @Test
    void findVueloById() {
        Vuelo vuelo  = Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Santa Marta").build();
        vuelo = vueloRepository.save(vuelo);

        Optional<Vuelo> vueloEncontrado = vueloRepository.findVueloById(vuelo.getId());
        assertTrue(vueloEncontrado.isPresent());
        assertEquals(vuelo.getId(), vueloEncontrado.get().getId());
    }

    @Test
    void findAllByOrderByOrigenAsc() {
        Vuelo vuelo1 = vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Medellin").build());
        Vuelo vuelo2 = vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Cali").destino("Cartagena").build());
        Vuelo vuelo3 = vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Armenia").destino("Pereira").build());

        List<Vuelo> vuelos = vueloRepository.findAllByOrderByOrigenAsc();
        assertEquals(vuelo3.getOrigen(), vuelos.get(0).getOrigen());
        assertEquals(vuelo1.getOrigen(), vuelos.get(1).getOrigen());
        assertEquals(vuelo2.getOrigen(), vuelos.get(2).getOrigen());
    }


    @Test
    void findByNumeroVuelo() {
        UUID numeroVuelo = UUID.randomUUID();
        Vuelo vuelo = vueloRepository.save(Vuelo.builder().numeroVuelo(numeroVuelo).origen("Bogota").destino("Cali").build());

        Optional<Vuelo> resultado = vueloRepository.findByNumeroVuelo(numeroVuelo);
        assertTrue(resultado.isPresent());
        assertEquals(vuelo.getId(), resultado.get().getId());
    }

    @Test
    void findByOrigenStartingWith() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Cali").build());
        List<Vuelo> resultado = vueloRepository.findByOrigenStartingWith("Bo");
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getOrigen().startsWith("Bo"));
    }

    @Test
    void findByDestinoStartingWith() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Cali").build());
        List<Vuelo> resultado = vueloRepository.findByDestinoStartingWith("Ca");
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getDestino().startsWith("Ca"));
    }

    @Test
    void findByOrigen() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Cartagena").build());
        List<Vuelo> resultado = vueloRepository.findByOrigen("Bogota");
        assertFalse(resultado.isEmpty());
        assertEquals("Bogota", resultado.get(0).getOrigen());
    }

    @Test
    void findByDestino() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Cali").destino("Cartagena").build());
        List<Vuelo> resultado = vueloRepository.findByDestino("Cartagena");
        assertFalse(resultado.isEmpty());
        assertEquals("Cartagena", resultado.get(0).getDestino());
    }

    @Test
    void findByDestinoAndOrigen() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Cali").destino("Cartagena").build());
        List<Vuelo> resultado = vueloRepository.findByDestinoAndOrigen("Cartagena", "Cali");
        assertFalse(resultado.isEmpty());
        assertEquals("Cartagena", resultado.get(0).getDestino());
        assertEquals("Cali", resultado.get(0).getOrigen());
    }

    @Test
    void findByDestinoIn() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Medellin").build());
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Cali").destino("Cartagena").build());
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Barranquilla").destino("Santa Marta").build());

        List<Vuelo> resultado = vueloRepository.findByDestinoIn("Cartagena", "Medellin", "Santa Marta");
        assertEquals(3, resultado.size());
        System.out.println(resultado);
    }

    @Test
    void findByDestinoOrOrigen() {
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Bogota").destino("Cali").build());
        vueloRepository.save(Vuelo.builder().numeroVuelo(UUID.randomUUID()).origen("Cali").destino("Medellin").build());

        List<Vuelo> resultado = vueloRepository.findByDestinoOrOrigen("Cali");
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }
}