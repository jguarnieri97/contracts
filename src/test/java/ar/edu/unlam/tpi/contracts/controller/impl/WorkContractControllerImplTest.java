package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.WorkContractController;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.TestUtils;
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
        var request = TestUtils.buildWorkContractRequest();
        var responseMock = TestUtils.buildWorkContractResponse();
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
        var contractId = TestUtils.CONTRACT_ID;
        var responseMock = TestUtils.buildWorkContractResponse();
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
        var contractId = TestUtils.CONTRACT_ID;
        var request = TestUtils.buildWorkContractUpdateRequest();
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