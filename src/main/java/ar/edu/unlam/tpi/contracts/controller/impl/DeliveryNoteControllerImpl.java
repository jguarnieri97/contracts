package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.DeliveryNoteController;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class DeliveryNoteControllerImpl implements DeliveryNoteController {

    private final DeliveryNoteService deliveryNoteService;

    @Override
    public GenericResponse<Void> createDeliveryNote(DeliveryNoteRequest deliveryNote) {
        deliveryNoteService.createDeliveryNote(deliveryNote);
        return new GenericResponse<>(
                Constants.STATUS_CREATED,
                Constants.CREATED_MESSAGE,
                null
        );
    }

    @Override
    public GenericResponse<DeliveryNoteResponse> getDeliveryNote(Long contractId) {
        DeliveryNoteResponse deliveryNote = deliveryNoteService.getDeliveryNote(contractId);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.SUCCESS_MESSAGE,
                deliveryNote
        );
    }

    @Override
    public GenericResponse<DeliveryNoteResponse> signatureDeliveryNote(Long contractId, @Valid DeliverySignatureRequest request) {
        DeliveryNoteResponse response = deliveryNoteService.signatureDeliveryNote(contractId, request);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.SUCCESS_MESSAGE,
                response
        );
    }
} 
