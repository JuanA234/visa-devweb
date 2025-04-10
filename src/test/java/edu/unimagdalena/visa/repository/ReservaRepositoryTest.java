package edu.unimagdalena.visa.repository;

import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.model.Reserva;
import edu.unimagdalena.visa.model.Vuelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservaRepositoryTest {
    @Autowired ReservaRepository reservaRepository;
    @Autowired VueloRepository vueloRepository;
    @Autowired PasajeroRepository pasajeroRepository;

    @Test
    void findById() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reservaGuardada = reservaRepository.save(reserva);

        Optional<Reserva> reservaEncontrada = reservaRepository.findById(reservaGuardada.getId());
        assertTrue(reservaEncontrada.isPresent());
        assertEquals(reserva.getCodigoReserva(), reservaEncontrada.get().getCodigoReserva());
    }

    @Test
    void findByCodigoReserva() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reservaGuardada = reservaRepository.save(reserva);

        Optional<Reserva> reservaEncontrada = reservaRepository.findByCodigoReserva(reservaGuardada.getCodigoReserva());
        assertTrue(reservaEncontrada.isPresent());
        assertEquals(reserva.getCodigoReserva(), reservaEncontrada.get().getCodigoReserva());
    }

    @Test
    void findByPasajero() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reservaGuardada = reservaRepository.save(reserva);

        List<Reserva> resultados = reservaRepository.findByPasajero(reservaGuardada.getPasajero());
        assertFalse(resultados.isEmpty());
        assertEquals(reservaGuardada.getCodigoReserva(), resultados.get(0).getCodigoReserva());
    }

    @Test
    void findByVuelo() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reservaGuardada = reservaRepository.save(reserva);

        List<Reserva> resultados = reservaRepository.findByVuelo(vuelo);
        assertFalse(resultados.isEmpty());
        assertEquals(vuelo.getId(), resultados.get(0).getVuelo().getId());
    }

    @Test
    void countByVuelo() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva1 = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reserva2 = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        Reserva reservaGuardada1 = reservaRepository.save(reserva1);
        Reserva reservaGuardada2 = reservaRepository.save(reserva2);

        Long conteo = reservaRepository.countByVuelo(vuelo);
        assertNotNull(conteo);
        assertEquals(2, conteo);
        System.out.println(conteo);
    }

    @Test
    void findReservasByPasajeroId() {
        Pasajero pasajero = Pasajero.builder().build();
        pasajero = pasajeroRepository.save(pasajero);

        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).pasajero(pasajero).build();
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findReservasByPasajeroId(pasajero.getId());
        assertFalse(reservas.isEmpty());
        assertEquals(pasajero.getId(), reservas.get(0).getPasajero().getId());
    }

    @Test
    void findReservasByVueloId() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findReservasByVueloId(vuelo.getId());
        assertFalse(reservas.isEmpty());
        assertEquals(vuelo.getId(), reservas.get(0).getVuelo().getId());
    }

    @Test
    void findAllReservasOrdenadasPorCodigo() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        UUID uuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID uuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

        Reserva reserva1 = Reserva.builder().codigoReserva(uuid1).vuelo(vuelo).build();
        Reserva reserva2 = Reserva.builder().codigoReserva(uuid2).vuelo(vuelo).build();

        reservaRepository.save(reserva1);
        reservaRepository.save(reserva2);

        List<Reserva> reservasOrdenadas = reservaRepository.findAllReservasOrdenadasPorCodigo();

        reservasOrdenadas.forEach(r -> System.out.println("Ordenado UUID: " + r.getCodigoReserva()));

        assertFalse(reservasOrdenadas.isEmpty());
        assertEquals(uuid1, reservasOrdenadas.get(0).getCodigoReserva());
        assertEquals(uuid2, reservasOrdenadas.get(1).getCodigoReserva());
    }

    @Test
    void countTotalReservas() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        reservaRepository.save(Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build());
        reservaRepository.save(Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build());

        Long totalReservas = reservaRepository.countTotalReservas();
        assertNotNull(totalReservas);
        assertEquals(2, totalReservas);
    }

    @Test
    void findReservasSinPasajero() {
        Vuelo vuelo = Vuelo.builder().build();
        vuelo = vueloRepository.save(vuelo);

        Reserva reserva = Reserva.builder().codigoReserva(UUID.randomUUID()).vuelo(vuelo).build();
        reservaRepository.save(reserva);

        List<Reserva> reservasSinPasajero = reservaRepository.findReservasSinPasajero();
        assertFalse(reservasSinPasajero.isEmpty());
        assertNull(reservasSinPasajero.get(0).getPasajero());
    }
}