package ar.edu.unlam.tpi.contracts.persistence.dao.impl;

import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class WorkContractDAOImplTest {

    @Mock
    private WorkContractRepository workContractRepository;

    @InjectMocks
    private WorkContractDAOImpl workContractDAO;

    @Test
    void givenValidId_whenFindWorkContractById_thenReturnWorkContract() {
        // Arrange
        Long contractId = 1L;
        WorkContractEntity contract = new WorkContractEntity();
        contract.setId(contractId);

        when(workContractRepository.findById(contractId)).thenReturn(Optional.of(contract));

        // Act
        WorkContractEntity result = workContractDAO.findWorkContractById(contractId);

        // Assert
        assertNotNull(result);
        assertEquals(contractId, result.getId());
        verify(workContractRepository, times(1)).findById(contractId);
    }

    @Test
    void givenInvalidId_whenFindWorkContractById_thenThrowContractNotFoundException() {
        // Arrange
        Long contractId = 1090L;

        when(workContractRepository.findById(contractId)).thenReturn(Optional.empty());

        // Act & Assert
        ContractNotFoundException exception = assertThrows(
                ContractNotFoundException.class,
                () -> workContractDAO.findWorkContractById(contractId)
        );

        assertEquals("NOT_FOUND", exception.getMessage());
        verify(workContractRepository, times(1)).findById(contractId);
    }

    @Test
    void givenValidWorkContract_whenSaveWorkContract_thenSaveSuccessfully() {
        // Given
        WorkContractEntity workContract = new WorkContractEntity();
        workContract.setId(1L);

        // When
        workContractDAO.saveWorkContract(workContract);

        // Then
        verify(workContractRepository, times(1)).save(workContract);
    }

}
