package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.util.Constants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryNoteControllerImplTest {

    @Mock
    private DeliveryNoteService deliveryNoteService;

    @InjectMocks
    private DeliveryNoteControllerImpl controller;


    @Test
    void givenNullRequest_whenCreateDeliveryNote_thenReturnsCreatedResponse() {
        // When
        GenericResponse<Void> response = controller.createDeliveryNote(null);
    
        // Then
        verify(deliveryNoteService).createDeliveryNote(null);
        assertThat(response.getCode()).isEqualTo(Constants.STATUS_CREATED);
        assertThat(response.getMessage()).isEqualTo(Constants.CREATED_MESSAGE);
        assertThat(response.getData()).isNull();
    }
    

    @Test
    void givenNullId_whenGetDeliveryNote_thenReturnsOkResponseWithNullData() {
        // Given
        when(deliveryNoteService.getDeliveryNote(null)).thenReturn(null);
    
        // When
        GenericResponse<DeliveryNoteResponse> response = controller.getDeliveryNote(null);
    
        // Then
        assertThat(response.getCode()).isEqualTo(Constants.STATUS_OK);
        assertThat(response.getMessage()).isEqualTo(Constants.SUCCESS_MESSAGE);
        assertThat(response.getData()).isNull();
    }
    
}