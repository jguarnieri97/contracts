package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.BlockchainResponse;
import ar.edu.unlam.tpi.contracts.dto.BlockchainVerifyRequest;

public interface BlockchainServiceClient {

    BlockchainResponse certificateFile(BlockchainRequest request);

    void verifyCertificate(BlockchainVerifyRequest request);

}
