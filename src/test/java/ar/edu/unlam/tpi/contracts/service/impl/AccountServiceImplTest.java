package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.util.ContractValidator;
import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private WorkContractRepository repository;

    @Mock
    private WorkContractConverter converter;

    @Mock
    private ContractValidator validator;

    @InjectMocks
    private AccountServiceImpl service;

    @Test
    void givenApplicantIdAndLimit_whenGetContractsByApplicantId_thenReturnLimitedContracts() {
        // Given
        Long applicantId = 1L;
        Boolean limit = true;
        WorkContractEntity contractEntity = new WorkContractEntity();
        WorkContractResponse contractResponse = WorkContractResponse.builder().build();

        when(repository.findByApplicantId(applicantId)).thenReturn(List.of(contractEntity));
        when(converter.convertToResponse(contractEntity)).thenReturn(contractResponse);

        // When
        List<WorkContractResponse> result = service.getContractsByApplicantId(applicantId, limit);

        // Then
        assertEquals(1, result.size());
        assertEquals(contractResponse, result.get(0));
        verify(repository, times(1)).findByApplicantId(applicantId);
        verify(validator, times(1)).validateContractsExist(anyList(), eq("applicantId"), eq(applicantId));
        verify(converter, times(1)).convertToResponse(contractEntity);
    }

    @Test
    void givenSupplierIdAndLimit_whenGetContractsBySupplierId_thenReturnLimitedContracts() {
        // Given
        Long supplierId = 1L;
        Boolean limit = true;
        WorkContractEntity contractEntity = new WorkContractEntity();
        WorkContractResponse contractResponse = WorkContractResponse.builder().build();

        when(repository.findBySupplierIdAndStates(supplierId)).thenReturn(List.of(contractEntity));
        when(converter.convertToResponse(contractEntity)).thenReturn(contractResponse);

        // When
        List<WorkContractResponse> result = service.getContractsBySupplierId(supplierId, limit);

        // Then
        assertEquals(1, result.size());
        assertEquals(contractResponse, result.get(0));
        verify(repository, times(1)).findBySupplierIdAndStates(supplierId);
        verify(validator, times(1)).validateContractsExist(anyList(), eq("supplierId"), eq(supplierId));
        verify(converter, times(1)).convertToResponse(contractEntity);
    }

    @Test
    void givenWorkerId_whenGetContractsByWorkerId_thenReturnContracts() {
        // Given
        Long workerId = 1L;
        LocalDate today = LocalDate.now();
        List<WorkStateEnum> validStates = List.of(WorkStateEnum.PENDING);
        WorkContractEntity contractEntity = new WorkContractEntity();
        WorkContractResponse contractResponse = WorkContractResponse.builder().build();

        when(repository.findByWorkersContaining(workerId, validStates, today)).thenReturn(List.of(contractEntity));
        when(converter.convertToResponse(contractEntity)).thenReturn(contractResponse);

        // When
        List<WorkContractResponse> result = service.getContractsByWorkerId(workerId);

        // Then
        assertEquals(1, result.size());
        assertEquals(contractResponse, result.get(0));
        verify(repository, times(1)).findByWorkersContaining(workerId, validStates, today);
        verify(validator, times(1)).validateContractsExist(anyList(), eq("workerId"), eq(workerId));
        verify(converter, times(1)).convertToResponse(contractEntity);
    }
}
