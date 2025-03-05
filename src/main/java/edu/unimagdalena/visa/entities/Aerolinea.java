package edu.unimagdalena.visa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="aerolineas")
public class Aerolinea {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "aerolinea_vuelo",
            joinColumns = @JoinColumn(name = "aerolinea_id"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id")
    )
    private Set<Vuelo> vuelos;
}
