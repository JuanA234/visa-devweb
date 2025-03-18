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
    }

    @Test
    void countPasajerosWithPasaporte() {
    }

    @Test
    void findPasajerosWithoutReservas() {
    }
}