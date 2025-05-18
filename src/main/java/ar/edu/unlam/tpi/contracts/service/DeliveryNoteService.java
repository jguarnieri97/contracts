package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteRequest;

public interface DeliveryNoteService {

    void createDeliveryNote(DeliveryNoteRequest deliveryNoteRequest);

}
