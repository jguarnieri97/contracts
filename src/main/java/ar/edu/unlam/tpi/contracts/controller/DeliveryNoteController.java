package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/contracts/v1/delivery-note")
public interface DeliveryNoteController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> createDeliveryNote(@RequestBody DeliveryNoteRequest deliveryNote);

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<DeliveryNoteResponse> getDeliveryNote(@PathVariable("id") Long id);

}
