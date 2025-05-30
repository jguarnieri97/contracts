package ar.edu.unlam.tpi.contracts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainVerifyRequest {

    private byte[] data;
    private String txHash;

}
