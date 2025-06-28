package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;

/**
 * Servicio para manejar los remitos
 */
public interface DeliveryNoteService {
    
    /**
     * Crea una nota de entrega basada en la solicitud proporcionada.
     * 
     * @param deliveryNoteRequest
     */
    void createDeliveryNote(DeliveryNoteRequest deliveryNoteRequest);

    /**
     * Recupera una nota de entrega para un ID de contrato específico.
     * @param contractId
     * @return
     */
    DeliveryNoteResponse getDeliveryNote(Long contractId);


    /**
     * Firma una nota de entrega para un ID de contrato específico.
     * 
     * @param id
     * @param request
     * @return
     */
    DeliveryNoteResponse signatureDeliveryNote(Long id, DeliverySignatureRequest request);
}
