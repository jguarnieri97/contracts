package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;

public interface DeliveryNoteService {

    void createDeliveryNote(DeliveryNoteRequest deliveryNoteRequest);

    DeliveryNoteResponse getDeliveryNote(Long contractId);

    void signatureDeliveryNote(Long id, DeliverySignatureRequest request);
}
