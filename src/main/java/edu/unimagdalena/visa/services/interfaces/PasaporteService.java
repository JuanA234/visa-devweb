package edu.unimagdalena.visa.services.interfaces;

import edu.unimagdalena.visa.entities.Pasaporte;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PasaporteService {
    //Cambiar por DTO en futuras versiones
    Pasaporte createPasaporte(Pasaporte pasaporte);
    List<Pasaporte> getPasaportesByNumero(String numero);
    Optional<Pasaporte> getPasaporteById(Long id);
    List<Pasaporte> getPasaportesByIdOrNumero(Long id, String numero);
    List<Pasaporte> getPasaportesByNumeroStartingWith(String prefix);
    List<Pasaporte> getPasaportesByNumeroContaining(String fragment);

}
