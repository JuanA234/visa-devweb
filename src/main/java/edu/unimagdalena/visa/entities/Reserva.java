package edu.unimagdalena.visa.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
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
    @JoinColumn(name = "vuelo_id", nullable = false, referencedColumnName = "id")
    private Vuelo vuelo;
}
