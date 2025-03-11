package edu.unimagdalena.visa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="reservas")
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID codigoReserva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pasajero_id", referencedColumnName = "id")
    private Pasajero pasajero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vuelo_id", referencedColumnName = "id", nullable = false)
    private Vuelo vuelo;
}
