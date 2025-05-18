package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/contracts/v1/delivery-note")
public interface DeliveryNoteController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> createDeliveryNote(@RequestBody DeliveryNoteRequest deliveryNote);

}
