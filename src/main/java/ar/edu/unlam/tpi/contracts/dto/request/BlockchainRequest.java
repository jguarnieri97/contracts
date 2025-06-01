package ar.edu.unlam.tpi.contracts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainRequest {

    private byte[] data;

}
