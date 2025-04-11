package edu.unimagdalena.visa.dto.Pasajero;

import jakarta.validation.constraints.NotBlank;

public record RequestPasajeroDTO(@NotBlank(message = "El nombre es obligatorio") String nombre,
                                 @NotBlank(message = "El nid es obligatorio") String nid) {
}
