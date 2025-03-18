package edu.unimagdalena.visa.services;

import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.entities.Pasaporte;
import edu.unimagdalena.visa.repositories.PasajeroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasajeroServiceTest {

    @Mock
    private PasajeroRepository pasajeroRepository;

    @InjectMocks
    private PasajeroService pasajeroService;

    public PasajeroServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPasajero() {
        Pasajero pasajero = new Pasajero(null,"Juan Avendaño","1234", new Pasaporte(), null);
        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(new Pasajero(1L ,"Juan Avendaño","1234", new Pasaporte(), null));

        Pasajero createdPasajero = pasajeroService.createPasajero(pasajero);

        assertNotNull(createdPasajero.getId());
        assertEquals("Juan Avendaño", createdPasajero.getNombre());
        assertEquals("1234", createdPasajero.getNid());
        verify(pasajeroRepository, times(1)).save(pasajero);
    }


    @Test
    void getPasajeroById() {
        Pasajero pasajero = new Pasajero(1L,"Juan Avendaño","1234", new Pasaporte(), null);
        when(pasajeroRepository.findPasajeroById(1L)).thenReturn(Optional.of(pasajero));

        Optional<Pasajero> foundPasajero = pasajeroService.getPasajeroById(1L);

        assertTrue(foundPasajero.isPresent());
        assertEquals("Juan Avendaño", foundPasajero.get().getNombre());
        verify(pasajeroRepository, times(1)).findPasajeroById(1L);
    }

    @Test
    void getPasajerosByNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("Juan").build();
        when(pasajeroRepository.findByNombre("Juan")).thenReturn(List.of(pasajero));

        List<Pasajero> foundPasajeros = pasajeroService.getPasajerosByNombre("Juan");

        assertFalse(foundPasajeros.isEmpty());
        assertEquals("Juan", foundPasajeros.get(0).getNombre());
        verify(pasajeroRepository, times(1)).findByNombre("Juan");
    }

    @Test
    void getPasajeroByNid() {
        Pasajero pasajero = new Pasajero(1L, "Juan Avendaño", "1234", new Pasaporte(), null);
        when(pasajeroRepository.findByNid("1234")).thenReturn(Optional.of(pasajero));

        Optional<Pasajero> foundPasajero = pasajeroService.getPasajeroByNid("1234");

        assertTrue(foundPasajero.isPresent());
        assertEquals("1234", foundPasajero.get().getNid());
        verify(pasajeroRepository, times(1)).findByNid("1234");
    }

    @Test
    void countPasajerosWithPasaporte() {
        when(pasajeroRepository.countPasajerosWithPasaporte()).thenReturn(1L);
        Long count = pasajeroService.countPasajerosWithPasaporte();
        assertEquals(1L, count);
        verify(pasajeroRepository, times(1)).countPasajerosWithPasaporte();
    }

    @Test
    void findPasajerosWithoutReservas() {
        Pasajero pasajero1 = new Pasajero(1L, "Carlos Pérez", "5678", new Pasaporte(), Set.of());
        Pasajero pasajero2 = new Pasajero(2L, "Ana Gómez", "91011", new Pasaporte(), Set.of());
        when(pasajeroRepository.findPasajerosWithoutReservas()).thenReturn(List.of(pasajero1, pasajero2));

        List<Pasajero> pasajeros = pasajeroService.getPasajerosWithoutReservas();

        assertEquals(2, pasajeros.size());
        verify(pasajeroRepository, times(1)).findPasajerosWithoutReservas();
    }
}