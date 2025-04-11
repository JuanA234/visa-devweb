package edu.unimagdalena.visa.controller;


import edu.unimagdalena.visa.dto.Pasajero.ResponsePasajeroDTO;
import edu.unimagdalena.visa.model.Pasajero;
import edu.unimagdalena.visa.service.impl.PasajeroServiceImpl;
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

//    @PostMapping
//    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero){
//        Pasajero createdPasajero = pasajeroService.createPasajero(pasajero);
//        return new ResponseEntity<>(createdPasajero, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePasajeroDTO> getPasajeroById(@PathVariable Long id){
        Optional<ResponsePasajeroDTO> pasajero = pasajeroService.getPasajeroById(id);
        return pasajero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
