package edu.unimagdalena.visa.service;

import edu.unimagdalena.visa.dto.Pasajero.RequestPasajeroDTO;
import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.mappers.PasajeroMapper;
import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.model.Pasaporte;
import edu.unimagdalena.visa.repository.PasajeroRepository;
import edu.unimagdalena.visa.service.impl.PasajeroServiceImpl;
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
        Pasajero pasajero = Pasajero.builder().nombre("Juan Avendaño").nid("1234").build();
        RequestPasajeroDTO requestPasajeroDTO = new RequestPasajeroDTO("Juan Avendaño", "1234");
        ResponsePasajeroDTO responsePasajeroDTO = new ResponsePasajeroDTO("Juan Avendaño", "1234");

        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(pasajero);
        when(pasajeroMapper.pasajeroToDTO(pasajero)).thenReturn(responsePasajeroDTO);
        when(pasajeroMapper.dtoToPasajero(requestPasajeroDTO)).thenReturn(pasajero);

        ResponsePasajeroDTO createdPasajero = pasajeroService.createPasajero(requestPasajeroDTO);

        assertNotNull(createdPasajero);
        assertEquals("Juan Avendaño", createdPasajero.nombre());
        assertEquals("1234", createdPasajero.nid());
        verify(pasajeroRepository, times(1)).save(pasajero);
    }


    @Test
    void getPasajeroById() {
        Pasajero pasajero = Pasajero.builder().id(1L).nombre("Juan Avendaño").nid("23rg").build();
        when(pasajeroRepository.findPasajeroById(1L)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToDTO(pasajero))
                .thenReturn(new ResponsePasajeroDTO( "Juan Avendaño", "23rg"));


        Optional<ResponsePasajeroDTO> foundPasajero = pasajeroService.getPasajeroById(1L);

        assertTrue(foundPasajero.isPresent());
        assertEquals("Juan Avendaño", foundPasajero.get().nombre());
        verify(pasajeroRepository, times(1)).findPasajeroById(1L);
    }

    @Test
    void getPasajerosByNombre() {
        String nombre = "Juan";
        Pasajero pasajero = Pasajero.builder().nombre(nombre).build();
        ResponsePasajeroDTO pasajeroDTO = new ResponsePasajeroDTO(nombre, null);

        when(pasajeroRepository.findByNombre(nombre)).thenReturn(List.of(pasajero));
        when(pasajeroMapper.toListDTO(List.of(pasajero))).thenReturn(List.of(pasajeroDTO));

        List<ResponsePasajeroDTO> foundPasajeros = pasajeroService.getPasajerosByNombre(nombre);

        assertFalse(foundPasajeros.isEmpty());
        verify(pasajeroRepository, times(1)).findByNombre(nombre);
    }

    @Test
    void getPasajeroByNid() {
        String nombre = "Juan Avendaño";
        String nid = "1234";

        Pasajero pasajero = new Pasajero(1L, nombre, nid, new Pasaporte(), null);
        ResponsePasajeroDTO pasajeroDTO = new ResponsePasajeroDTO(nombre, nid);

        when(pasajeroRepository.findByNid(nid)).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToDTO(pasajero))
                .thenReturn(pasajeroDTO);

        Optional<ResponsePasajeroDTO> foundPasajero = pasajeroService.getPasajeroByNid(nid);
        assertTrue(foundPasajero.isPresent());
        verify(pasajeroRepository, times(1)).findByNid(nid);
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

        ResponsePasajeroDTO pasajeroDTO1 = new ResponsePasajeroDTO("Carlos Pérez", "5678");
        ResponsePasajeroDTO pasajeroDTO2 = new ResponsePasajeroDTO("Ana Gómez", "91011");

        when(pasajeroRepository.findPasajerosWithoutReservas()).thenReturn(List.of(pasajero1, pasajero2));
        when(pasajeroMapper.toListDTO(List.of(pasajero1, pasajero2))).thenReturn(List.of(pasajeroDTO1, pasajeroDTO2));

        List<ResponsePasajeroDTO> pasajeros = pasajeroService.getPasajerosWithoutReservas();

        assertEquals(2, pasajeros.size());
        assertEquals("Carlos Pérez", pasajeros.get(0).nombre());
        verify(pasajeroRepository, times(1)).findPasajerosWithoutReservas();
    }

    @Test
    void getPasajeroByNidAndNombre() {

        Pasajero pasajero = Pasajero.builder().nid("777").nombre("Marcos").build();
        ResponsePasajeroDTO pasajeroDTO = new ResponsePasajeroDTO("Marcos", "777");

        when(pasajeroRepository.findByNidAndNombre("777", "Marcos")).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToDTO(pasajero)).thenReturn(pasajeroDTO);

        Optional<ResponsePasajeroDTO> foundPasajero = pasajeroService.getPasajeroByNidAndNombre("777", "Marcos");
        assertTrue(foundPasajero.isPresent());
        assertEquals("777", foundPasajero.get().nid());
        assertEquals("Marcos", foundPasajero.get().nombre());
        verify(pasajeroRepository, times(1)).findByNidAndNombre("777", "Marcos");
    }

    @Test
    void getPasajeroDistintByNombre() {
        Pasajero pasajero = Pasajero.builder().nombre("Marcos").build();
        ResponsePasajeroDTO pasajeroDTO = new ResponsePasajeroDTO("Marcos", "777");

        when(pasajeroRepository.findDistinctByNombre("Marcos")).thenReturn(Optional.of(pasajero));
        when(pasajeroMapper.pasajeroToDTO(pasajero)).thenReturn(pasajeroDTO);

        Optional<ResponsePasajeroDTO> foundPasajero = pasajeroService.getPasajeroDistintByNombre("Marcos");

        assertTrue(foundPasajero.isPresent());
        assertEquals("Marcos", foundPasajero.get().nombre());
        verify(pasajeroRepository, times(1)).findDistinctByNombre("Marcos");
    }

    @Test
    void getPasajerosConNombres() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Pedro").build();

        List<Pasajero> pasajeros = List.of(pasajero1, pasajero2);

        ResponsePasajeroDTO pasajeroDTO1 = new ResponsePasajeroDTO("Juan", null);
        ResponsePasajeroDTO pasajeroDTO2 = new ResponsePasajeroDTO("Pedro", null);

        List<ResponsePasajeroDTO> pasajerosDtos = List.of(pasajeroDTO1, pasajeroDTO2);

        when(pasajeroRepository.findPasajerosConNombres("Juan", "Pedro")).thenReturn(List.of(pasajero1, pasajero2));
        when(pasajeroMapper.toListDTO(pasajeros)).thenReturn(pasajerosDtos);

        List<ResponsePasajeroDTO> responsePasajeroDTOList = pasajeroService.getPasajerosConNombres("Juan", "Pedro");

        assertFalse(responsePasajeroDTOList.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Pedro");
        assertTrue(nombres.contains(responsePasajeroDTOList.get(0).nombre()));
        verify(pasajeroRepository, times(1)).findPasajerosConNombres("Juan", "Pedro");
    }

    @Test
    void getPasajeroByNombreStartingWith() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Julian").build();

        ResponsePasajeroDTO pasajeroDTO1 = new ResponsePasajeroDTO("Juan", null);
        ResponsePasajeroDTO pasajeroDTO2 = new ResponsePasajeroDTO("Julian", null);

        when(pasajeroRepository.findByNombreStartingWith("Ju")).thenReturn(List.of(pasajero1, pasajero2));
        when(pasajeroMapper.toListDTO(List.of(pasajero1, pasajero2))).thenReturn(List.of(pasajeroDTO1, pasajeroDTO2));

        List<ResponsePasajeroDTO> pasajeros = pasajeroService.getPasajeroByNombreStartingWith("Ju");

        assertFalse(pasajeros.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Julian");
        assertTrue(nombres.contains(pasajeros.get(0).nombre()));
        verify(pasajeroRepository, times(1)).findByNombreStartingWith("Ju");
    }

    @Test
    void getPasajeroByNombreContainingIgnoreCase() {
        Pasajero pasajero1 = Pasajero.builder().nombre("Juan").build();
        Pasajero pasajero2 = Pasajero.builder().nombre("Juancho").build();

        ResponsePasajeroDTO pasajeroDTO1 = new ResponsePasajeroDTO("Juan", null);
        ResponsePasajeroDTO pasajeroDTO2 = new ResponsePasajeroDTO("Juancho", null);



        when(pasajeroRepository.findByNombreContainingIgnoreCase("uan")).thenReturn(List.of(pasajero1, pasajero2));
        when(pasajeroMapper.toListDTO(List.of(pasajero1, pasajero2))).thenReturn(List.of(pasajeroDTO1, pasajeroDTO2));

        List<ResponsePasajeroDTO> pasajeros = pasajeroService.getPasajeroByNombreContainingIgnoreCase("uan");

        assertFalse(pasajeros.isEmpty());
        List<String> nombres = Arrays.asList("Juan", "Juancho");
        assertTrue(nombres.contains(pasajeros.get(0).nombre()));
        verify(pasajeroRepository, times(1)).findByNombreContainingIgnoreCase("uan");
    }
}