package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "work_contracts") 
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class WorkContractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column
    private Double price;

    @Column
    private Instant date;

    @Enumerated(EnumType.STRING)
    private WorkState state;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;

    @ElementCollection
    @CollectionTable(name = "contract_workers", joinColumns = @JoinColumn(name = "contract_id"))
    @Column(name = "workers")
    private List<Long> workers = new ArrayList<>();

    public WorkContractEntity(Double price, Instant date, WorkState state, Long supplierId, Long applicantId, List<Long> workers) {
        this.price = price;
        this.date = date;
        this.state = state;
        this.supplierId = supplierId;
        this.applicantId = applicantId;
        this.workers = workers;
    }

}
