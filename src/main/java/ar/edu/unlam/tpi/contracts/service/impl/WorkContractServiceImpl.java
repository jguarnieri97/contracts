package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import org.springframework.stereotype.Service;

@Service
public class WorkContractServiceImpl implements WorkContractService {

    private final WorkContractRepository repository;

    public WorkContractServiceImpl(WorkContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkContractResponse createContract(WorkContractRequest request) {
        WorkContractEntity contract = new WorkContractEntity(
                request.getPrice(),
                request.getDate(),
                WorkState.PENDING, 
                request.getSupplierId(),
                request.getApplicantId(),
                request.getWorkers()
        );

       
        WorkContractEntity saved = repository.save(contract);

       
        return WorkContractResponse.builder()
                .id(saved.getId())
                .price(saved.getPrice())
                .date(saved.getDate())
                .state(saved.getState().name())
                .supplierId(saved.getSupplierId())
                .applicantId(saved.getApplicantId())
                .workers(saved.getWorkers())
                .build();
    }
}
