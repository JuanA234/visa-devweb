package edu.unimagdalena.visa.dto.Aerolinea;

import jakarta.validation.constraints.NotBlank;

public record RequestAerolineaDTO(@NotBlank(message = "El nombre es obligatorio.") String nombre) {
}
