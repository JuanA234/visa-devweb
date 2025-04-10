package edu.unimagdalena.visa.service.impl;

import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.mappers.PasajeroMapper;
import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.repository.PasajeroRepository;
import edu.unimagdalena.visa.service.interfaces.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {
    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;

    @Autowired
    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper){
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    public Pasajero createPasajero(Pasajero pasajero){
        return pasajeroRepository.save(pasajero);
    }

    public Optional<ResponsePasajeroDTO> getPasajeroById(Long id){
        Optional<Pasajero> pasajero = pasajeroRepository.findPasajeroById(id);
        Optional<ResponsePasajeroDTO> dto = pasajero.map(pasajeroMapper::pasajeroToDTO);
        return dto;
    }

    public List<Pasajero> getPasajerosByNombre(String nombre){
        return pasajeroRepository.findByNombre(nombre);
    }

    public Optional<Pasajero> getPasajeroByNid(String nid){
        return pasajeroRepository.findByNid(nid);
    }

    public Optional<Pasajero> getPasajeroByNidAndNombre(String nid, String nombre){
        return pasajeroRepository.findByNidAndNombre(nid, nombre);
    }

    public Optional<Pasajero> getPasajeroDistintByNombre(String nombre){
        return  pasajeroRepository.findDistinctByNombre(nombre);
    }

    public List<Pasajero> getPasajerosConNombres(String nombre1, String nombre2){
        return pasajeroRepository.findPasajerosConNombres(nombre1, nombre2);
    }

    public List<Pasajero> getPasajeroByNombreStartingWith(String prefix){
        return pasajeroRepository.findByNombreStartingWith(prefix);
    }

    public Long countPasajerosWithPasaporte(){
        return pasajeroRepository.countPasajerosWithPasaporte();
    }

    public List<Pasajero> getPasajerosWithoutReservas(){
        return pasajeroRepository.findPasajerosWithoutReservas();
    }

    public List<Pasajero> getPasajeroByNombreContainingIgnoreCase(String fragment){
        return pasajeroRepository.findByNombreContainingIgnoreCase(fragment);
    }
}
