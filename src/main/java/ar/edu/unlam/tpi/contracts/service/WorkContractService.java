package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;

public interface WorkContractService {
    WorkContractResponse createContract(WorkContractRequest request);
}