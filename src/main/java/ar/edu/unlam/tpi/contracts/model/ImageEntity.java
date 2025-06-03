package ar.edu.unlam.tpi.contracts.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "images")
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
    @Column(nullable = false)
    private byte[] data;

    public ImageEntity(byte[] data) {
        this.data = data;
    }
}