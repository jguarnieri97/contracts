package ar.edu.unlam.tpi.contracts.service;

import java.util.List;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;

public interface WorkContractService {
    WorkContractResponse createContract(WorkContractRequest request);

    List<WorkContractResponse> getContractsByApplicantId(Long id);

    List<WorkContractResponse> getContractsBySupplierId(Long supplierId);

    void updateContractState(Long id, WorkContractUpdateRequest request);

}