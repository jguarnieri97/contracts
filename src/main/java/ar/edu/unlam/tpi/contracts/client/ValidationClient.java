package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.request.RegisterRequest;

public interface ValidationClient {

    void registerWork(RegisterRequest request);

}
