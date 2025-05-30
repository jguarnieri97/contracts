package ar.edu.unlam.tpi.contracts.dto.response;

import lombok.Data;

@Data
public class BlockchainResponse {

    private String txHash;
    private String dataHash;
    private String blockNumber;

}
