package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
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

    private final WorkContractDAO workContractRepository;
    private final WorkContractConverter converter;
    private final ContractValidator validator;
    private static final int DEFAULT_LIMIT = 4;

    @Override
    public List<WorkContractInfoResponse> getContractsByApplicantId(Long applicantId, Boolean limit) {
        List<WorkContractEntity> contracts = workContractRepository.findByApplicantId(applicantId);
        validator.validateContractsExist(contracts, "applicantId", applicantId);

        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(converter::convertToInfoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractInfoResponse> getContractsBySupplierId(Long supplierId, Boolean limit) {
        List<WorkContractEntity> contracts = workContractRepository.findBySupplierId(supplierId);
        validator.validateContractsExist(contracts, "supplierId", supplierId);

        return contracts.stream()
                .limit(Boolean.TRUE.equals(limit) ? DEFAULT_LIMIT : Long.MAX_VALUE)
                .map(converter::convertToInfoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkContractInfoResponse> getContractsByWorkerId(Long workerId, String range) {
        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end;

    switch (range.toLowerCase()) {
        //desde el dia de hoy hasta los proximos 7 dias
        case "week" -> {
            start = today;
            end = today.plusDays(7);

        }
        case "month" -> {
            start = today.withDayOfMonth(1);
            end = today.withDayOfMonth(today.lengthOfMonth());
        }
        default -> { //day
            start = today;
            end = today;
        }
    }

    List<WorkStateEnum> validStates = List.of(WorkStateEnum.PENDING);
    List<WorkContractEntity> contracts = workContractRepository.findByWorkersContainingStatesAndDateRange(
            workerId, validStates, start, end
    );

    return contracts.stream()
            .map(converter::convertToInfoResponse)
            .collect(Collectors.toList());
    }


}
