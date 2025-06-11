package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "CONTRACT_IMAGE", schema = "CONTRACTS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "img_data", nullable = false)
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private WorkContractEntity workContract;

    public ImageEntity(byte[] data, WorkContractEntity contract) {
        this.data = data;
        this.workContract = contract;
    }
}