package ar.edu.unlam.tpi.contracts.service;

import java.util.List;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;

public interface WorkContractService {
    WorkContractResponse createContract(WorkContractRequest request);

    List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Integer limit);

    List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Integer limit);

    List<WorkContractResponse> getContractsByWorkerId(Long workerId);

    void updateContractState(Long id, WorkContractUpdateRequest request);

}