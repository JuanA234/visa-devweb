package edu.unimagdalena.visa.services.interfaces;

import edu.unimagdalena.visa.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    //Cambiar por DTO en futuras versiones
    Aerolinea createAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> getAerolineaById(Long id);
    List<Aerolinea> getAerolineasByNombre(String nombre);
    List<Aerolinea> getAerolineasConNombreInicio(String inicioNombre);
    List<Aerolinea> getAerolineasConNombreFinal(String finNombre);
    List<Aerolinea> getAerolineasConNombreConteniendo(String parteNombre);
}
