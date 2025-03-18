package edu.unimagdalena.visa.controllers;


import edu.unimagdalena.visa.entities.Pasajero;
import edu.unimagdalena.visa.services.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    @Autowired
    public PasajeroController(PasajeroService pasajeroService){
        this.pasajeroService = pasajeroService;
    }

    @PostMapping
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero){
        Pasajero createdPasajero = pasajeroService.createPasajero(pasajero);
        return new ResponseEntity<>(createdPasajero, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pasajero> getPasajeroById(@PathVariable Long id){
        Optional<Pasajero> pasajero = pasajeroService.getPasajeroById(id);
        return pasajero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
