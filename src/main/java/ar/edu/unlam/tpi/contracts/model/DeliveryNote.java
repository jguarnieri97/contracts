package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DELIVERY_NOTE")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "deliveryNote")
    private WorkContractEntity workContract;

    @Lob
    private byte[] data;

    @Setter
    private String txHash;

    @Setter
    private String dataHash;

    @Setter
    private String blockNumber;

    private LocalDateTime createdAt;

    public DeliveryNote(WorkContractEntity workContract, byte[] data) {
        this.workContract = workContract;
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }
}
