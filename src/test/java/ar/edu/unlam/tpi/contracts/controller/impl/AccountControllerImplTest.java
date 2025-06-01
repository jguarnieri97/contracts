package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.AccountController;
import ar.edu.unlam.tpi.contracts.service.AccountService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.WorkContratDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class AccountControllerImplTest {
    private AccountService accountService;
    private AccountController controller;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        controller = new AccountControllerImpl(accountService);
    }

    @Test
    void getContractsByApplicantIdReturnsGenericResponseWithOkStatus() {
        // Arrange
        var applicantId = 2L;
        var limit = true;
        var responseMock = List.of(WorkContratDataHelper.createWorkContractResponse());
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
    void getContractsBySupplierIdReturnsGenericResponseWithOkStatus() {
        // Arrange
        var supplierId = 1L;
        var limit = true;
        var responseMock = List.of(WorkContratDataHelper.createWorkContractResponse());
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
    void getContractsByWorkerIdReturnsGenericResponseWithOkStatus() {
        // Arrange
        var workerId = 3L;
        var responseMock = List.of(WorkContratDataHelper.createWorkContractResponse());
        when(accountService.getContractsByWorkerId(workerId)).thenReturn(responseMock);

        // Act
        var response = controller.getContractsByWorkerId(workerId);

        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(responseMock, response.getData());

        verify(accountService).getContractsByWorkerId(workerId);
    }
}
