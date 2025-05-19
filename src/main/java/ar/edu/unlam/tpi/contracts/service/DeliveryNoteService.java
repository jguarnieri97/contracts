package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteResponse;

public interface DeliveryNoteService {

    void createDeliveryNote(DeliveryNoteRequest deliveryNoteRequest);

    DeliveryNoteResponse getDeliveryNote(Long contractId);
}
