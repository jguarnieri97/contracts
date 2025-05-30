package ar.edu.unlam.tpi.contracts.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryNoteResponse {
    private Long id;
    private byte[] data;
    private String txHash;
    private String dataHash;
    private String blockNumber;
    private String createdAt;
}
