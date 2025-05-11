package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
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
                request.getDateFrom(),
                request.getDateTo(),
                WorkState.PENDING, 
                request.getDetail(),
                request.getSupplierId(),
                request.getApplicantId(),
                request.getWorkers()
        );

       
        WorkContractEntity saved = repository.save(contract);

       
        return convertToResponse(saved);
    }

    @Override
    public WorkContractResponse getContractById(Long id) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));
        return convertToResponse(contract);
    }
    
    private WorkContractResponse convertToResponse(WorkContractEntity entity) { //convierte el entity a response
        return WorkContractResponse.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .dateFrom(entity.getDateFrom())
                .dateTo(entity.getDateTo())
                .state(entity.getState().name())
                .detail(entity.getDetail())
                .supplierId(entity.getSupplierId())
                .applicantId(entity.getApplicantId())
                .workers(entity.getWorkers())
                .build();
    }
    
    @Override
    public void updateContractState(Long id, WorkContractUpdateRequest request) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));

        try {
            WorkState newState = WorkState.valueOf(request.getState().toUpperCase());
            contract.setState(newState);
            repository.save(contract);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido: " + request.getState());
        }
    }



}
