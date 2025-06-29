package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DELIVERY_NOTE", schema = "CONTRACTS")
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private WorkContractEntity workContract;

    @Setter
    @Column(name = "note_data")
    private byte[] data;

    @Setter
    @Column(name = "tx_hash")
    private String txHash;

    @Setter
    @Column(name = "data_hash")
    private String dataHash;

    @Setter
    @Column(name = "block_number")
    private String blockNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "is_signed")
    private boolean isSigned;

    public DeliveryNote(WorkContractEntity workContract, byte[] data) {
        this.workContract = workContract;
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }
}
