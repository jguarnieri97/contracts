package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;

import java.util.List;
import java.util.stream.Collectors;

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

       
        return WorkContractResponse.builder()
                .id(saved.getId())
                .price(saved.getPrice())
                .dateFrom(saved.getDateFrom())
                .dateTo(saved.getDateTo())
                .state(saved.getState().name())
                .detail(saved.getDetail())
                .supplierId(saved.getSupplierId())
                .applicantId(saved.getApplicantId())
                .workers(saved.getWorkers())
                .build();
    }

    @Override
    public List<WorkContractResponse> getContractsByApplicantId(Long applicantId) {
        List<WorkContractEntity> contracts = repository.findByApplicantId(applicantId);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para el applicantId: " + applicantId);
        }
        return contracts.stream()
                .limit(4)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
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

    @Override
    public List<WorkContractResponse> getContractsBySupplierId(Long supplierId) {
        List<WorkState> validStates = List.of(WorkState.PENDING, WorkState.INITIATED);
        List<WorkContractEntity> contracts = repository.findBySupplierIdAndStateIn(supplierId, validStates);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos activos para el supplierId: " + supplierId);
        }
        return contracts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private WorkContractResponse convertToResponse(WorkContractEntity entity) {
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

}
