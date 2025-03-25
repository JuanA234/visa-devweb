package edu.unimagdalena.visa.services;

import edu.unimagdalena.visa.dto.PasajeroDTO;
import edu.unimagdalena.visa.dto.mappers.PasajeroMapper;
import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.entities.Pasaporte;
import edu.unimagdalena.visa.repositories.PasajeroRepository;
import edu.unimagdalena.visa.services.impl.PasajeroServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasajeroServiceImplTest {

    @Mock
    private PasajeroRepository pasajeroRepository;

    @Mock
    private PasajeroMapper pasajeroMapper;

    @InjectMocks
    private PasajeroServiceImpl pasajeroService;

    public PasajeroServiceImplTest() {
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
        Pasajero pasajero = Pasajero.builder().id(1L).nombre("Juan Avendaño").nid("23rg").build();
        when(pasajeroRepository.findPasajeroById(1L)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToDTO(pasajero))
                .thenReturn(new PasajeroDTO( "Juan Avendaño", "23rg"));


        Optional<PasajeroDTO> foundPasajero = pasajeroService.getPasajeroById(1L);

        assertTrue(foundPasajero.isPresent());
        assertEquals("Juan Avendaño", foundPasajero.get().nombre());
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
    void getPasajerosWithoutReservas() {
        Pasajero pasajero1 = new Pasajero(1L, "Carlos Pérez", "5678", new Pasaporte(), Set.of());
        Pasajero pasajero2 = new Pasajero(2L, "Ana Gómez", "91011", new Pasaporte(), Set.of());
        when(pasajeroRepository.findPasajerosWithoutReservas()).thenReturn(List.of(pasajero1, pasajero2));

        List<Pasajero> pasajeros = pasajeroService.getPasajerosWithoutReservas();

        assertEquals(2, pasajeros.size());
        verify(pasajeroRepository, times(1)).findPasajerosWithoutReservas();
    }

    @Test
    void getPasajeroByNidAndNombre() {
        Pasajero pasajero = Pasajero.builder().nid("777").nombre("Marcos").build();
        when(pasajeroRepository.findByNidAndNombre("777", "Marcos")).thenReturn(Optional.of(pasajero));

        Optional<Pasajero> foundPasajero = pasajeroService.getPasajeroByNidAndNombre("777", "Marcos");
        assertTrue(foundPasajero.isPresent());
        assertEquals("777", foundPasajero.get().getNid());
        assertEquals("Marcos", foundPasajero.get().getNombre());
        verify(pasajeroRepository, times(1)).findByNidAndNombre("777", "Marcos");
    }

    @Test
    void getPasajeroDistintByNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("Marcos").build();
        when(pasajeroRepository.findDistinctByNombre("Marcos")).thenReturn(Optional.of(pasajero));

        Optional<Pasajero> foundPasajero = pasajeroService.getPasajeroDistintByNombre("Marcos");
        assertTrue(foundPasajero.isPresent());
        assertEquals("Marcos", foundPasajero.get().getNombre());
        verify(pasajeroRepository, times(1)).findDistinctByNombre("Marcos");
    }

    @Test
    void getPasajerosConNombres() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Pedro").build();
        when(pasajeroRepository.findPasajerosConNombres("Juan", "Pedro")).thenReturn(List.of(pasajero1, pasajero2));

        List<Pasajero> pasajeros = pasajeroService.getPasajerosConNombres("Juan", "Pedro");

        assertFalse(pasajeros.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Pedro");
        assertTrue(nombres.contains(pasajeros.get(0).getNombre()));
        verify(pasajeroRepository, times(1)).findPasajerosConNombres("Juan", "Pedro");
    }

    @Test
    void getPasajeroByNombreStartingWith() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Julian").build();
        when(pasajeroRepository.findByNombreStartingWith("Ju")).thenReturn(List.of(pasajero1, pasajero2));

        List<Pasajero> pasajeros = pasajeroService.getPasajeroByNombreStartingWith("Ju");

        assertFalse(pasajeros.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Julian");
        assertTrue(nombres.contains(pasajeros.get(0).getNombre()));
        verify(pasajeroRepository, times(1)).findByNombreStartingWith("Ju");
    }

    @Test
    void getPasajeroByNombreContainingIgnoreCase() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Juancho").build();
        when(pasajeroRepository.findByNombreContainingIgnoreCase("uan")).thenReturn(List.of(pasajero1, pasajero2));

        List<Pasajero> pasajeros = pasajeroService.getPasajeroByNombreContainingIgnoreCase("uan");

        assertFalse(pasajeros.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Juancho");
        assertTrue(nombres.contains(pasajeros.get(0).getNombre()));
        verify(pasajeroRepository, times(1)).findByNombreContainingIgnoreCase("uan");
    }
}