package edu.unimagdalena.visa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="pasaportes")
public class Pasaporte {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String numero;

    @OneToOne(mappedBy = "pasaporte")
    private Pasajero pasajero;
}
