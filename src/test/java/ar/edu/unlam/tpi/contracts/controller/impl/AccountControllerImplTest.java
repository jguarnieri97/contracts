package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.service.AccountService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.WorkContractDataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountControllerImplTest {
    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountControllerImpl controller;

    @Test
    void givenApplicantId_whenGetContractsByApplicantId_thenReturnsGenericResponseWithOkStatus() {
        // Arrange
        var applicantId = 2L;
        var limit = true;
        var responseMock = List.of(WorkContractDataHelper.createWorkContractInfoResponse());
        when(accountService.getContractsByApplicantId(applicantId, limit)).thenReturn(responseMock);
    
        // Act
        var response = controller.getContractsByApplicantId(applicantId, limit);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());
    
        verify(accountService).getContractsByApplicantId(applicantId, limit);
    }
    
    @Test
    void givenSupplierId_whenGetContractsBySupplierId_thenReturnsGenericResponseWithOkStatus() {
        // Arrange
        var supplierId = 1L;
        var limit = true;
        var responseMock = List.of(WorkContractDataHelper.createWorkContractInfoResponse());
        when(accountService.getContractsBySupplierId(supplierId, limit)).thenReturn(responseMock);
    
        // Act
        var response = controller.getContractsBySupplierId(supplierId, limit);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());
    
        verify(accountService).getContractsBySupplierId(supplierId, limit);
    }
    
    @Test
    void givenWorkerIdAndRange_whenGetContractsByWorkerId_thenReturnsGenericResponseWithOkStatus() {
        // Arrange
        Long workerId = 3L;
        String range = "week";
        List<WorkContractInfoResponse> responseMock = List.of(WorkContractDataHelper.createWorkContractInfoResponse());
    
        when(accountService.getContractsByWorkerId(workerId, range)).thenReturn(responseMock);
    
        // Act
        var response = controller.getContractsByWorkerId(workerId, range);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());
    
        verify(accountService).getContractsByWorkerId(workerId, range);
    }
    
}
