package ar.edu.unlam.tpi.contracts.persistence.dao.impl;

import ar.edu.unlam.tpi.contracts.exception.WorkContractRepositoryException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkContractDAOImplTest {

    @Mock
    private WorkContractRepository workContractRepository;

    @InjectMocks
    private WorkContractDAOImpl workContractDAO;

    @Test
    void givenContractsInRepository_whenFindAll_thenReturnsList() {
        List<WorkContractEntity> contracts = List.of(new WorkContractEntity());
        when(workContractRepository.findAll()).thenReturn(contracts);

        List<WorkContractEntity> result = workContractDAO.findAll();

        assertEquals(contracts, result);
        verify(workContractRepository).findAll();
    }

    @Test
    void givenRepositoryThrowsException_whenFindAll_thenThrowsException() {
        when(workContractRepository.findAll()).thenThrow(new RuntimeException("error"));
        assertThrows(RuntimeException.class, () -> workContractDAO.findAll());
    }

    @Test
    void givenExistingId_whenFindById_thenReturnsContract() {
        WorkContractEntity contract = new WorkContractEntity();
        contract.setId(1L);
        when(workContractRepository.findById(1L)).thenReturn(Optional.of(contract));

        WorkContractEntity result = workContractDAO.findById(1L);

        assertEquals(1L, result.getId());
        verify(workContractRepository).findById(1L);
    }

    @Test
    void givenNonExistingId_whenFindById_thenThrowsException() {
        when(workContractRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(WorkContractRepositoryException.class, () -> workContractDAO.findById(2L));
    }

    @Test
    void givenValidContract_whenSave_thenReturnsSavedContract() {
        WorkContractEntity contract = new WorkContractEntity();
        when(workContractRepository.save(contract)).thenReturn(contract);

        WorkContractEntity result = workContractDAO.save(contract);

        assertEquals(contract, result);
        verify(workContractRepository).save(contract);
    }

    @Test
    void givenRepositoryThrowsException_whenSave_thenThrowsException() {
        WorkContractEntity contract = new WorkContractEntity();
        when(workContractRepository.save(contract)).thenThrow(new RuntimeException("error"));
        assertThrows(WorkContractRepositoryException.class, () -> workContractDAO.save(contract));
    }

    @Test
    void givenExistingId_whenDelete_thenDeletesContract() {
        doNothing().when(workContractRepository).deleteById(1L);
        workContractDAO.delete(1L);
        verify(workContractRepository).deleteById(1L);
    }

    @Test
    void givenRepositoryThrowsException_whenDelete_thenThrowsException() {
        doThrow(new RuntimeException("error")).when(workContractRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> workContractDAO.delete(1L));
    }

    @Test
    void givenApplicantId_whenFindByApplicantId_thenReturnContracts() {
        List<WorkContractEntity> contracts = List.of(new WorkContractEntity());
        when(workContractRepository.findByApplicantId(1L)).thenReturn(contracts);

        List<WorkContractEntity> result = workContractDAO.findByApplicantId(1L);

        assertEquals(contracts, result);
        verify(workContractRepository).findByApplicantId(1L);
    }

    @Test
    void givenSupplierId_whenFindBySupplierId_thenReturnContracts() {
        List<WorkContractEntity> contracts = List.of(new WorkContractEntity());
        when(workContractRepository.findBySupplierId(1L)).thenReturn(contracts);

        List<WorkContractEntity> result = workContractDAO.findBySupplierId(1L);

        assertEquals(contracts, result);
        verify(workContractRepository).findBySupplierId(1L);
    }

    @Test
    void givenWorkerIdStatesAndDateRange_whenFindByWorkersContainingStatesAndDateRange_thenReturnContracts() {
        List<WorkContractEntity> contracts = List.of(new WorkContractEntity());
        when(workContractRepository.findByWorkersContainingStatesAndDateRange(
                eq(1L), anyList(), any(), any())).thenReturn(contracts);

        List<WorkContractEntity> result = workContractDAO.findByWorkersContainingStatesAndDateRange(
                1L, Arrays.asList(WorkStateEnum.PENDING), LocalDate.now(), LocalDate.now());

        assertEquals(contracts, result);
        verify(workContractRepository).findByWorkersContainingStatesAndDateRange(
                eq(1L), anyList(), any(), any());
    }

}