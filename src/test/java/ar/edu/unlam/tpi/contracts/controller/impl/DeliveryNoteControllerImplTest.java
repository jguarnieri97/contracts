package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.DeliveryNoteController;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class DeliveryNoteControllerImplTest {

    private DeliveryNoteController controller;
    private DeliveryNoteService deliveryNoteService;

    @BeforeEach
    void setUp() {
        deliveryNoteService = mock(DeliveryNoteService.class);
        controller = new DeliveryNoteControllerImpl(deliveryNoteService);
    }

    @org.junit.jupiter.api.Test
    void returnsCreatedResponseWhenRequestIsNull() {
        GenericResponse<Void> response = controller.createDeliveryNote(null);

        verify(deliveryNoteService).createDeliveryNote(null);
        assertThat(response.getCode()).isEqualTo(Constants.STATUS_CREATED);
        assertThat(response.getMessage()).isEqualTo(Constants.CREATED_MESSAGE);
        assertThat(response.getData()).isNull();
    }

    @org.junit.jupiter.api.Test
    void returnsOkResponseWhenGetDeliveryNoteWithNullId() {
        when(deliveryNoteService.getDeliveryNote(null)).thenReturn(null);

        GenericResponse<DeliveryNoteResponse> response = controller.getDeliveryNote(null);

        assertThat(response.getCode()).isEqualTo(Constants.STATUS_OK);
        assertThat(response.getMessage()).isEqualTo(Constants.SUCCESS_MESSAGE);
        assertThat(response.getData()).isNull();
    }
}