package ar.edu.unlam.tpi.contracts.service;


import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;

public interface WorkContractService {
    WorkContractResponse createContract(WorkContractRequest request);

    void updateContractState(Long id, WorkContractUpdateRequest request);

}