package edu.unimagdalena.visa.controllers;


import edu.unimagdalena.visa.dto.PasajeroDTO;
import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.services.impl.PasajeroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    private final PasajeroServiceImpl pasajeroService;

    @Autowired
    public PasajeroController(PasajeroServiceImpl pasajeroService){
        this.pasajeroService = pasajeroService;
    }

    @PostMapping
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero){
        Pasajero createdPasajero = pasajeroService.createPasajero(pasajero);
        return new ResponseEntity<>(createdPasajero, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasajeroDTO> getPasajeroById(@PathVariable Long id){
        Optional<PasajeroDTO> pasajero = pasajeroService.getPasajeroById(id);
        return pasajero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
