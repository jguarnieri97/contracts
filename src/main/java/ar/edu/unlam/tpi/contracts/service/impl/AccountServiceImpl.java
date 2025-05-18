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
    private static final int DEFAULT_LIMIT = 4;

    public AccountServiceImpl(WorkContractRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Boolean limit) {
        LocalDate today = LocalDate.now();
        List<WorkContractEntity> contracts = repository.findByApplicantIdAndToday(applicantId, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para hoy para el applicantId: " + applicantId);
        }
        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Boolean limit) {
        LocalDate today = LocalDate.now();
        List<WorkState> validStates = List.of(WorkState.FINALIZED, WorkState.INITIATED);
        List<WorkContractEntity> contracts = repository.findBySupplierIdAndStateIn(supplierId, validStates, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos activos para hoy para el supplierId: " + supplierId);
        }
        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractResponse> getContractsByWorkerId(Long workerId) {
        LocalDate today = LocalDate.now();
        List<WorkState> validStates = List.of(WorkState.PENDING);
        List<WorkContractEntity> contracts = repository.findByWorkersContaining(workerId, validStates, today);
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para hoy para el workerId: " + workerId);
        }
        return contracts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private WorkContractResponse convertToResponse(WorkContractEntity entity) {
        List<String> base64Images = entity.getFiles().stream()
        .map(img -> java.util.Base64.getEncoder().encodeToString(img.getData()))
        .toList();

        return WorkContractResponse.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .dateFrom(entity.getDateFrom())
                .dateTo(entity.getDateTo())
                .state(entity.getState().name())
                .detail(entity.getDetail())
                .supplierId(entity.getSupplierId())
                .applicantId(entity.getApplicantId())
                .files(base64Images)
                .workers(entity.getWorkers())
                .build();
    }
}
