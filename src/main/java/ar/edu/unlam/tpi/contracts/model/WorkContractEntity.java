package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "WORK_CONTRACT", schema = "CONTRACTS")
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

    @Column(name = "price")
    private Double price;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "contract_detail")
    private String detail;

    @Column(name = "contract_sate")
    private WorkStateEnum state;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;

    @ElementCollection
    @CollectionTable(name = "contract_workers", joinColumns = @JoinColumn(name = "contract_id"))
    @Column(name = "worker_id")
    private List<Long> workers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> files = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DeliveryNote deliveryNote;

}
