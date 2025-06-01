package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.CodeNumberGeneratorService;
import ar.edu.unlam.tpi.contracts.util.ContractValidator;
import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import ar.edu.unlam.tpi.contracts.util.WorkContractValidator;
import ar.edu.unlam.tpi.contracts.util.WorkContratDataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkContractServiceImplTest {

    @Mock
    private WorkContractRepository repository;

    @Mock
    private WorkContractConverter converter;

    @Mock
    private WorkContractValidator validator;

    @Mock
    private CodeNumberGeneratorService codeNumberGenerator;

    @InjectMocks
    private WorkContractServiceImpl service;

    @Test
    void givenValidRequest_whenCreateContract_thenReturnSavedContract() {
        // Given
        WorkContractRequest request = WorkContratDataHelper.createWorkContractRequest();
        WorkContractResponse expectedResponse = WorkContratDataHelper.createWorkContractResponse();

        WorkContractEntity contractEntity = new WorkContractEntity(
                "CODE123",
                request.getPrice(),
                request.getDateFrom(),
                request.getDateTo(),
                WorkStateEnum.PENDING,
                request.getDetail(),
                request.getSupplierId(),
                request.getApplicantId(),
                request.getWorkers()
        );

        when(codeNumberGenerator.generateCodeNumber()).thenReturn("CODE123");
        when(repository.save(any(WorkContractEntity.class))).thenReturn(contractEntity);
        when(converter.convertToResponse(contractEntity)).thenReturn(expectedResponse);

        // When
        WorkContractResponse result = service.createContract(request);

        // Then
        assertEquals(expectedResponse, result);
        verify(codeNumberGenerator, times(1)).generateCodeNumber();
        verify(repository, times(1)).save(any(WorkContractEntity.class));
        verify(converter, times(1)).convertToResponse(contractEntity);
    }

    @Test
    void givenValidIdAndUpdateRequest_whenUpdateContractState_thenUpdateContract() {
        // Given
        Long contractId = 1L;
        WorkContractUpdateRequest updateRequest = WorkContratDataHelper.createWorkContractUpdateRequest();
        WorkContractEntity existingContract = new WorkContractEntity(
                "CODE123",
                150000.0,
                LocalDate.of(2025, 5, 13),
                LocalDate.of(2025, 5, 15),
                WorkStateEnum.PENDING,
                "Trabajo de prueba",
                1L,
                2L,
                List.of(3L, 4L, 5L)
        );

        when(repository.findById(contractId)).thenReturn(java.util.Optional.of(existingContract));

        // When
        service.updateContractState(contractId, updateRequest);

        // Then
        verify(validator, times(1)).validateStateTransition(existingContract, updateRequest);
        verify(repository, times(1)).save(existingContract);
        assertEquals(WorkStateEnum.PENDING, existingContract.getState());
    }

    @Test
    void givenValidId_whenGetContractById_thenReturnContractResponse() {
        // Given
        Long contractId = 1L;
        WorkContractEntity contractEntity = new WorkContractEntity();
        WorkContractResponse expectedResponse = WorkContractResponse.builder().build();

        when(repository.findById(contractId)).thenReturn(java.util.Optional.of(contractEntity));
        when(converter.convertToResponse(contractEntity)).thenReturn(expectedResponse);

        // When
        WorkContractResponse result = service.getContractById(contractId);

        // Then
        assertEquals(expectedResponse, result);
        verify(repository, times(1)).findById(contractId);
        verify(converter, times(1)).convertToResponse(contractEntity);
    }

    @Test
    void givenInvalidId_whenGetContractById_thenThrowContractNotFoundException() {
        // Given
        Long contractId = 1L;

        when(repository.findById(contractId)).thenReturn(java.util.Optional.empty());

        // When / Then
        assertThrows(ContractNotFoundException.class, () -> service.getContractById(contractId));
        verify(repository, times(1)).findById(contractId);
        verifyNoInteractions(converter);
    }
}
