package edu.unimagdalena.visa.service.impl;

import edu.unimagdalena.visa.dto.Aerolinea.RequestAerolineaDTO;
import edu.unimagdalena.visa.dto.Aerolinea.ResponseAerolineaDTO;
import edu.unimagdalena.visa.mappers.AerolineaMapper;
import edu.unimagdalena.visa.model.Aerolinea;
import edu.unimagdalena.visa.repository.AerolineaRepository;
import edu.unimagdalena.visa.service.interfaces.AerolineaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private final AerolineaRepository aerolineaRepository;
    private final AerolineaMapper aerolineaMapper;

    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository, AerolineaMapper aerolineaMapper) {
        this.aerolineaRepository = aerolineaRepository;
        this.aerolineaMapper = aerolineaMapper;
    }

    @Override
    public ResponseAerolineaDTO createAerolinea(RequestAerolineaDTO dto) {
        Aerolinea aerolinea = aerolineaMapper.toEntity(dto);
        Aerolinea aerolineaCreated = aerolineaRepository.save(aerolinea);
        return aerolineaMapper.toDTO(aerolineaCreated);
    }

    @Override
    public Optional<ResponseAerolineaDTO> getAerolineaById(Long id) {
        return aerolineaRepository.findById(id).map(aerolineaMapper::toDTO);
    }

    @Override
    public List<ResponseAerolineaDTO> getAerolineasByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre).stream()
                .map(aerolineaMapper::toDTO).toList();

    }

    @Override
    public List<ResponseAerolineaDTO> getAerolineasStartingWith(String inicioNombre) {
        return aerolineaRepository.findByNombreStartingWith(inicioNombre)
                .stream().map(aerolineaMapper::toDTO).toList();
    }

    @Override
    public List<ResponseAerolineaDTO> getAerolineasContaining(String parteNombre) {
        return aerolineaRepository.findByNombreContaining(parteNombre)
                .stream().map(aerolineaMapper::toDTO).toList();
    }

    @Override
    public List<ResponseAerolineaDTO> getAerolineasWithVueloOrigen(String origen) {
        return aerolineaRepository.findAerolineasWithVueloOrigen(origen)
                .stream().map(aerolineaMapper::toDTO).toList();
    }

    @Override
    public List<ResponseAerolineaDTO> getAerolineasWithVueloDestino(String destino) {
        return aerolineaRepository.findAerolineasWithVueloDestino(destino)
                .stream().map(aerolineaMapper::toDTO).toList();
    }

    @Override
    public Long getCountVuelosByAerolinea(Long id) {
        if (aerolineaRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se encontró la aerolinea,");
        }
        return aerolineaRepository.countVuelosByAerolinea(id);
    }

    @Override
    public Optional<ResponseAerolineaDTO> updateAerolinea(RequestAerolineaDTO dto, Long id) {
        return aerolineaRepository.findById(id).map(aerolinea -> {
            aerolineaMapper.updateEntityFromDTO(dto, aerolinea);
            Aerolinea aerolineaUpdated = aerolineaRepository.save(aerolinea);
            return aerolineaMapper.toDTO(aerolineaUpdated);
        });
    }

    @Override
    public void deleteAerolinea(Long id) {
        Optional<Aerolinea> aerolinea = aerolineaRepository.findById(id);
        if (aerolinea.isEmpty()){
            throw new EntityNotFoundException("No se encontró un registro con ese id.");
        }
        aerolineaRepository.deleteById(id);
    }
}
