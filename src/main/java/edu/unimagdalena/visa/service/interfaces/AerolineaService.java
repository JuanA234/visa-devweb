package edu.unimagdalena.visa.service.interfaces;


import edu.unimagdalena.visa.dto.Aerolinea.RequestAerolineaDTO;
import edu.unimagdalena.visa.dto.Aerolinea.ResponseAerolineaDTO;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {

    ResponseAerolineaDTO createAerolinea(RequestAerolineaDTO dto);

    Optional<ResponseAerolineaDTO> getAerolineaById(Long id);

    List<ResponseAerolineaDTO> getAerolineasByNombre(String nombre);

    List<ResponseAerolineaDTO> getAerolineasStartingWith(String inicioNombre);

    List<ResponseAerolineaDTO> getAerolineasContaining(String parteNombre);

    List<ResponseAerolineaDTO> getAerolineasWithVueloOrigen(String origen);

    List<ResponseAerolineaDTO> getAerolineasWithVueloDestino(String destino);

    Long getCountVuelosByAerolinea(Long id);

    Optional<ResponseAerolineaDTO> updateAerolinea(RequestAerolineaDTO dto, Long id);

    void deleteAerolinea(Long id);

}
