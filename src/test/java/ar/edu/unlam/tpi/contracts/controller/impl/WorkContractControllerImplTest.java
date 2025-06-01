package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.WorkContractController;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.WorkContratDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkContractControllerImplTest {

    private WorkContractService workContractService;
    private WorkContractController controller;

    @BeforeEach
    void setUp() {
        workContractService = mock(WorkContractService.class);
        controller = new WorkContractControllerImpl(workContractService);
    }

    @Test
    void createContractReturnsGenericResponseWithCreatedStatus() {
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
    void getContractByIdReturnsGenericResponseWithOkStatus() {
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
    void updateContractStateReturnsGenericResponseWithOkStatus() {
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