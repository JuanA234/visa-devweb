package edu.unimagdalena.visa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
