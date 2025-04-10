package edu.unimagdalena.visa.mappers;

import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.model.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PasajeroMapper {
    ResponsePasajeroDTO pasajeroToDTO(Pasajero pasajero);

    Pasajero dtoToPasajero(ResponsePasajeroDTO dto);
}
