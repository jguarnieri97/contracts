package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DELIVERY_NOTE", schema = "CONTRACTS")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "contract_id", nullable = false)
    @OneToOne(mappedBy = "deliveryNote")
    private WorkContractEntity workContract;

    @Lob
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

    public DeliveryNote(WorkContractEntity workContract, byte[] data) {
        this.workContract = workContract;
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }
}
