package edu.unimagdalena.visa.mappers;

import edu.unimagdalena.visa.dto.Pasajero.RequestPasajeroDTO;
import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.model.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PasajeroMapper {

    ResponsePasajeroDTO pasajeroToDTO(Pasajero pasajero);

    Pasajero dtoToPasajero(RequestPasajeroDTO dto);

    List<ResponsePasajeroDTO> toListDTO(List<Pasajero> pasajeros);
}
