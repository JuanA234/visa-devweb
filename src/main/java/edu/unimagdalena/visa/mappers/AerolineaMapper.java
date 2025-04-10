package edu.unimagdalena.visa.mappers;

import edu.unimagdalena.visa.dto.Aerolinea.ResponseAerolineaDTO;
import edu.unimagdalena.visa.dto.Aerolinea.RequestAerolineaDTO;
import edu.unimagdalena.visa.model.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AerolineaMapper {

    Aerolinea toEntity(RequestAerolineaDTO dto);

    Aerolinea updateEntityFromDTO(RequestAerolineaDTO dto, @MappingTarget Aerolinea aerolinea);

    ResponseAerolineaDTO toDTO(Aerolinea aerolinea);
}
