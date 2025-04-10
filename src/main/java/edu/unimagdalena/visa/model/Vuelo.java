package edu.unimagdalena.visa.model;

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
    @Column(unique = true)
    private UUID numeroVuelo;
    private String origen;
    private String destino;

    @OneToMany(mappedBy = "vuelo")
    @Column(nullable = false)
    private Set<Reserva> reservas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id", referencedColumnName = "id", nullable = false)
    private Aerolinea aerolinea;
}
