package edu.unimagdalena.visa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="vuelos")
public class Vuelo{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID numeroVuelo;
    private String origen;
    private String destino;

    @OneToMany(mappedBy = "vuelo")
    @Column(nullable = false)
    private Set<Reserva> reservas;

    @ManyToMany(mappedBy = "vuelos")
    @Column(nullable = false)
    private Set<Aerolinea> aerolineas;
}
