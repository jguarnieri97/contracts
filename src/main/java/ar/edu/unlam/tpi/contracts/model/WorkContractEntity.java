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

    @Column(name = "contract_state")
    private WorkStateEnum state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplierEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id", nullable = false)
    private ApplicantEntity applicantEntity;

    @ElementCollection
    @CollectionTable(name = "contract_workers", schema = "CONTRACTS", joinColumns = @JoinColumn(name = "contract_id"))
    @Column(name = "worker_id")
    private List<Long> workers = new ArrayList<>();

    @OneToMany(mappedBy = "workContract", orphanRemoval = true)
    private List<ImageEntity> files = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "workContract", orphanRemoval = true)
    private DeliveryNote deliveryNote;

}
