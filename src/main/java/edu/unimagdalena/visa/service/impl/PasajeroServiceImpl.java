package edu.unimagdalena.visa.service.impl;

import edu.unimagdalena.visa.dto.Pasajero.RequestPasajeroDTO;
import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.mappers.PasajeroMapper;
import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.repository.PasajeroRepository;
import edu.unimagdalena.visa.service.interfaces.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasajeroServiceImpl implements PasajeroService {
    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;

    @Autowired
    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper){
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    public ResponsePasajeroDTO createPasajero(RequestPasajeroDTO dto){
        Pasajero pasajero = pasajeroMapper.dtoToPasajero(dto);
        Pasajero pasajeroCreated = pasajeroRepository.save(pasajero);
        return pasajeroMapper.pasajeroToDTO(pasajeroCreated);
    }

    public Optional<ResponsePasajeroDTO> getPasajeroById(Long id){
        Optional<Pasajero> pasajero = pasajeroRepository.findPasajeroById(id);
        Optional<ResponsePasajeroDTO> dto = pasajero.map(pasajeroMapper::pasajeroToDTO);
        return dto;
    }

    public List<ResponsePasajeroDTO> getPasajerosByNombre(String nombre){
        return pasajeroMapper.toListDTO(pasajeroRepository.findByNombre(nombre));
    }

    public Optional<ResponsePasajeroDTO> getPasajeroByNid(String nid){
        return pasajeroRepository.findByNid(nid).map(pasajeroMapper::pasajeroToDTO);
    }

    public Optional<ResponsePasajeroDTO> getPasajeroByNidAndNombre(String nid, String nombre){
        return pasajeroRepository.findByNidAndNombre(nid, nombre).map(pasajeroMapper::pasajeroToDTO);
    }

    public Optional<ResponsePasajeroDTO> getPasajeroDistintByNombre(String nombre){
        return  pasajeroRepository.findDistinctByNombre(nombre).map(pasajeroMapper::pasajeroToDTO);
    }

    public List<ResponsePasajeroDTO> getPasajerosConNombres(String nombre1, String nombre2){
        return pasajeroMapper.toListDTO(pasajeroRepository.findPasajerosConNombres(nombre1, nombre2));
    }

    public List<ResponsePasajeroDTO> getPasajeroByNombreStartingWith(String prefix){
        return pasajeroMapper.toListDTO(pasajeroRepository.findByNombreStartingWith(prefix));
    }

    public Long countPasajerosWithPasaporte(){
        return pasajeroRepository.countPasajerosWithPasaporte();
    }

    public List<ResponsePasajeroDTO> getPasajerosWithoutReservas() {
        return pasajeroMapper.toListDTO(pasajeroRepository.findPasajerosWithoutReservas());
    }

    public List<ResponsePasajeroDTO> getPasajeroByNombreContainingIgnoreCase(String fragment){
        return pasajeroMapper.toListDTO(pasajeroRepository.findByNombreContainingIgnoreCase(fragment));
    }
}
