package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteNotFoundException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.service.FileCreatorService;
import ar.edu.unlam.tpi.contracts.service.task.DeliveryNoteExecutorTask;
import ar.edu.unlam.tpi.contracts.util.DeliveryNoteDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryNoteServiceImplTest {

    private DeliveryNoteService service;
    private WorkContractDAO repository;
    private BlockchainServiceClient blockchainClient;
    private ExecutorService executorService;
    private FileCreatorService fileCreatorService;

    @BeforeEach
    void setUp() {
        repository = mock(WorkContractDAO.class);
        blockchainClient = mock(BlockchainServiceClient.class);
        executorService = mock(ExecutorService.class);
        fileCreatorService = mock(FileCreatorService.class);
        service = new DeliveryNoteServiceImpl(repository, blockchainClient, executorService, fileCreatorService);
    }

    @Test
    void throwsExceptionWhenDeliveryNoteDoesNotExist() {
        Long contractId = 123L;
        var contract = mock(WorkContractEntity.class);
        when(repository.findById(contractId)).thenReturn(contract);
        when(contract.getDeliveryNote()).thenReturn(null);

        assertThrows(DeliveryNoteNotFoundException.class,
                () -> service.getDeliveryNote(contractId));
    }

    @Test
    void createsDeliveryNoteAndExecutesAsyncTask() {
        DeliveryNoteRequest request = DeliveryNoteDataHelper.createDeliveryNoteRequest();
        WorkContractEntity contract = mock(WorkContractEntity.class);

        when(repository.findById(1L)).thenReturn(contract);

        service.createDeliveryNote(request);

        verify(repository).save(contract);
        verify(executorService).execute(any(DeliveryNoteExecutorTask.class));
    }

    @Test
    void returnsDeliveryNoteResponseWhenDeliveryNoteExists() {
        Long contractId = 1L;
        WorkContractEntity contract = mock(WorkContractEntity.class);
        DeliveryNote deliveryNote = mock(DeliveryNote.class);

        when(repository.findById(contractId)).thenReturn(contract);
        when(contract.getDeliveryNote()).thenReturn(deliveryNote);
        when(deliveryNote.getTxHash()).thenReturn("txHash");
        when(deliveryNote.getData()).thenReturn(new byte[]{1,2,3});
        when(deliveryNote.getId()).thenReturn(10L);
        when(deliveryNote.getDataHash()).thenReturn("hash");
        when(deliveryNote.getBlockNumber()).thenReturn("8000");
        when(deliveryNote.getCreatedAt()).thenReturn(java.time.LocalDateTime.now());

        var response = service.getDeliveryNote(contractId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(10L);
        verify(blockchainClient).verifyCertificate(any(BlockchainVerifyRequest.class));
    }


}