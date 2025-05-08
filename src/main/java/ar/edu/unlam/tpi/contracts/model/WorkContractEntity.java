package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Table(name = "work_contracts") // Nombre de la tabla
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class WorkContractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id") // Nombre de columna
    private Long id;

    private Double price;

    private Instant date;

    @Enumerated(EnumType.STRING)
    private WorkState state;

    public WorkContractEntity(Double price, Instant date, WorkState state) {
        this.price = price;
        this.date = date;
        this.state = state;
    }

}
