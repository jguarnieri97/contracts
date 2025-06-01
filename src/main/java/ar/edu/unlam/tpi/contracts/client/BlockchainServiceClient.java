package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.request.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.response.BlockchainResponse;

public interface BlockchainServiceClient {

    BlockchainResponse certificateFile(BlockchainRequest request);

    void verifyCertificate(BlockchainVerifyRequest request);

}
