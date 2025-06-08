package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteNotFoundException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.DeliveryNoteDAO;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.FileCreatorService;
import ar.edu.unlam.tpi.contracts.util.DeliveryNoteDataHelper;
import ar.edu.unlam.tpi.contracts.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.concurrent.ExecutorService;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryNoteServiceImplTest {

    @Mock
    private WorkContractDAO repository;
    
    @Mock
    private BlockchainServiceClient blockchainClient;
    
    @Mock
    private ExecutorService executorService;
    
    @Mock
    private FileCreatorService fileCreatorService;
    
    
    @Mock
    private DeliveryNoteDAO deliveryNoteDAO;

    
    @InjectMocks
    private DeliveryNoteServiceImpl service;
    
    @Test
    void givenContractWithoutDeliveryNote_whenGetDeliveryNote_thenThrowsException() {
        Long contractId = 123L;
        var contract = mock(WorkContractEntity.class);
        when(repository.findById(contractId)).thenReturn(contract);
        when(contract.getDeliveryNote()).thenReturn(null);
    
        assertThrows(DeliveryNoteNotFoundException.class,
                () -> service.getDeliveryNote(contractId));
    }
    
    @Test
    void givenValidRequest_whenCreateDeliveryNote_thenSavesContractAndExecutesAsyncTask() {
        DeliveryNoteRequest request = DeliveryNoteDataHelper.createDeliveryNoteRequest();
        WorkContractEntity contract = mock(WorkContractEntity.class);
    
        when(repository.findById(1L)).thenReturn(contract);
    
        service.createDeliveryNote(request);
    
        verify(repository).save(contract);
    }
    
    @Test
    void givenContractWithDeliveryNote_whenGetDeliveryNote_thenReturnsDeliveryNoteResponse() {
        Long contractId = 1L;
        WorkContractEntity contract = mock(WorkContractEntity.class);
        DeliveryNote deliveryNote = DeliveryNoteDataHelper.createDeliveryNote();
    
        when(repository.findById(contractId)).thenReturn(contract);
        when(contract.getDeliveryNote()).thenReturn(deliveryNote);

        var response = service.getDeliveryNote(contractId);
    
        assertThat(response).isNotNull();
        verify(blockchainClient).verifyCertificate(any(BlockchainVerifyRequest.class));
    }

    @Test
    void givenContractWithDeliveryNote_whenIsNotSigned_thenNotGoToBlockchain() {
        Long contractId = 1L;
        WorkContractEntity contract = mock(WorkContractEntity.class);
        DeliveryNote deliveryNote = DeliveryNoteDataHelper.createDeliveryNote();
        deliveryNote.setSigned(false);

        when(repository.findById(contractId)).thenReturn(contract);
        when(contract.getDeliveryNote()).thenReturn(deliveryNote);

        var response = service.getDeliveryNote(contractId);

        assertThat(response).isNotNull();
        verify(blockchainClient, never()).verifyCertificate(any(BlockchainVerifyRequest.class));
    }
    

}