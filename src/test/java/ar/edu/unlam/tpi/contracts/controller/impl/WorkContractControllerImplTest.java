package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.WorkContratDataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkContractControllerImplTest {

    @Mock
    private WorkContractService workContractService;
    
    @InjectMocks
    private WorkContractControllerImpl controller;
    
    @Test
    void givenValidRequest_whenCreateContract_thenReturnsGenericResponseWithCreatedStatus() {
        // Arrange
        var request = WorkContratDataHelper.createWorkContractRequest();
        var responseMock = WorkContratDataHelper.createWorkContractResponse();
        when(workContractService.createContract(request)).thenReturn(responseMock);
    
        // Act
        var response = controller.createContract(request);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_CREATED, response.getCode());
        assertEquals(Constants.CREATED_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());
    
        verify(workContractService).createContract(request);
    }
    
    @Test
    void givenContractId_whenGetContractById_thenReturnsGenericResponseWithOkStatus() {
        // Arrange
        var contractId = 1L;
        var responseMock = WorkContratDataHelper.createWorkContractResponse();
        when(workContractService.getContractById(contractId)).thenReturn(responseMock);
    
        // Act
        var response = controller.getContractById(contractId);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());
    
        verify(workContractService).getContractById(contractId);
    }
    
    @Test
    void givenContractIdAndRequest_whenUpdateContractState_thenReturnsGenericResponseWithOkStatus() {
        // Arrange
        var contractId = 1L;
        var request = WorkContratDataHelper.createWorkContractUpdateRequest();
        doNothing().when(workContractService).updateContractState(contractId, request);
    
        // Act
        var response = controller.updateContractState(contractId, request);
    
        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.UPDATED_MESSAGE, response.getMessage());
        assertNull(response.getData());
    
        verify(workContractService).updateContractState(contractId, request);
    }
    

}