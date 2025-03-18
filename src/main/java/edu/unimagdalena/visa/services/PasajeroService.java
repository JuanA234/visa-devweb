package edu.unimagdalena.visa.services;

import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.repositories.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;

    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository){
        this.pasajeroRepository = pasajeroRepository;
    }

    public Pasajero createPasajero(Pasajero pasajero){
        return pasajeroRepository.save(pasajero);
    }

    public Optional<Pasajero> getPasajeroById(Long id){
        return pasajeroRepository.findPasajeroById(id);
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

    public List<Pasajero> findPasajerosWithoutReservas(){
        return pasajeroRepository.findPasajerosWithoutReservas();
    }

    public List<Pasajero> getPasajeroByNombreContainingIgnoreCase(String fragment){
        return pasajeroRepository.findByNombreContainingIgnoreCase(fragment);
    }
}
