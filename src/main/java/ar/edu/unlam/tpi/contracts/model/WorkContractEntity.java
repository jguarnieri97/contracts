package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "work_contracts") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class WorkContractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "code_number", unique = true, nullable = false)
    private String codeNumber;

    @Column
    private Double price;

    @Column
    private LocalDate dateFrom;
    
    @Column
    private LocalDate dateTo;

    @Column
    private String detail;

    @Enumerated(EnumType.STRING)
    private WorkStateEnum state;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;

    @ElementCollection
    @CollectionTable(name = "contract_workers", joinColumns = @JoinColumn(name = "contract_id"))
    @Column(name = "workers")
    private List<Long> workers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contract_id")
    private List<ImageEntity> files = new ArrayList<>();

    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DeliveryNote deliveryNote;

}
