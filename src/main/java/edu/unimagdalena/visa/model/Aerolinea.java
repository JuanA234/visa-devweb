package edu.unimagdalena.visa.model;

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

    @OneToMany(mappedBy = "aerolinea")
    private Set<Vuelo> vuelos;
}
