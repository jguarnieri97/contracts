package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.AccountService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    private final WorkContractRepository repository;

    public AccountServiceImpl(WorkContractRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Integer limit) {
        LocalDate today = LocalDate.now();
        List<WorkContractEntity> contracts = repository.findByApplicantIdAndToday(applicantId, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para hoy para el applicantId: " + applicantId);
        }
        return contracts.stream()
                .limit(limit != null ? limit : Long.MAX_VALUE)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Integer limit) {
        LocalDate today = LocalDate.now();
        List<WorkState> validStates = List.of(WorkState.FINALIZED, WorkState.INITIATED);
        List<WorkContractEntity> contracts = repository.findBySupplierIdAndStateIn(supplierId, validStates, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos activos para hoy para el supplierId: " + supplierId);
        }
        return contracts.stream()
                .limit(limit != null ? limit : Long.MAX_VALUE)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

  

    @Override
    public List<WorkContractResponse> getContractsByWorkerId(Long workerId) {
        LocalDate today = LocalDate.now();
        List<WorkContractEntity> contracts = repository.findByWorkersContaining(workerId, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para hoy para el workerId: " + workerId);
        }
        return contracts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
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
}
