package edu.unimagdalena.visa.controller;

import edu.unimagdalena.visa.dto.Aerolinea.RequestAerolineaDTO;
import edu.unimagdalena.visa.dto.Aerolinea.ResponseAerolineaDTO;
import edu.unimagdalena.visa.service.interfaces.AerolineaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    public ResponseEntity<ResponseAerolineaDTO> createAerolinea(@Valid @RequestBody RequestAerolineaDTO aerolinea){
        ResponseAerolineaDTO response = aerolineaService.createAerolinea(aerolinea);
        URI location = URI.create("/api/v1/aerolinea/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

}
