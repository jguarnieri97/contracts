package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "TASK", schema = "CONTRACTS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private WorkContractEntity workContract;
}