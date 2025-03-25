package edu.unimagdalena.visa.dto.mappers;

import edu.unimagdalena.visa.dto.PasajeroDTO;
import edu.unimagdalena.visa.entities.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PasajeroMapper {
    PasajeroDTO pasajeroToDTO(Pasajero pasajero);

    Pasajero dtoToPasajero(PasajeroDTO dto);
}
