package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.AccountService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unlam.tpi.contracts.util.ContractValidator;
import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final WorkContractRepository repository;
    private final WorkContractConverter converter;
    private final ContractValidator validator;
    private static final int DEFAULT_LIMIT = 4;

    @Override
    public List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Boolean limit) {
        List<WorkContractEntity> contracts = repository.findByApplicantId(applicantId);
        validator.validateContractsExist(contracts, "applicantId", applicantId);

        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(converter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Boolean limit) {
        List<WorkContractEntity> contracts = repository.findBySupplierIdAndStates(supplierId);
        validator.validateContractsExist(contracts, "supplierId", supplierId);

        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(converter::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractResponse> getContractsByWorkerId(Long workerId) {
        LocalDate today = LocalDate.now();
        List<WorkStateEnum> validStates = List.of(WorkStateEnum.PENDING);
        List<WorkContractEntity> contracts = repository.findByWorkersContaining(workerId, validStates, today);
        validator.validateContractsExist(contracts, "workerId", workerId);

        return contracts.stream()
                .map(converter::convertToResponse)
                .collect(Collectors.toList());
    }

}
