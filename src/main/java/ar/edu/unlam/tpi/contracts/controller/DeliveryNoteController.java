package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/contracts/v1/delivery-note")
public interface DeliveryNoteController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> createDeliveryNote(@RequestBody DeliveryNoteRequest deliveryNote);

    @GetMapping("{contractId}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<DeliveryNoteResponse> getDeliveryNote(@PathVariable("contractId") Long contractId);

    @PutMapping("{contractId}")
    GenericResponse<Void> signatureDeliveryNote(
            @PathVariable("contractId") Long contractId,
            @RequestBody DeliverySignatureRequest request);

}
