package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.BlockchainResponse;

public interface BlockchainServiceClient {

    BlockchainResponse certificateFile(BlockchainRequest request);

}
