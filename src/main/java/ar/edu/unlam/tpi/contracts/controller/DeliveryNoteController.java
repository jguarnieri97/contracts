package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar las notas de entrega
 */
@RequestMapping("/contracts/v1/delivery-note")
@Validated
public interface DeliveryNoteController {

    /**
     * Crea una nueva nota de entrega
     * 
     * @param deliveryNote Datos de la nota de entrega a crear
     * @return Respuesta de confirmación
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> createDeliveryNote(@RequestBody DeliveryNoteRequest deliveryNote);

    /**
     * Obtiene una nota de entrega por ID de contrato
     * 
     * @param contractId ID del contrato asociado a la nota de entrega
     * @return Respuesta con los datos de la nota de entrega
     */
    @GetMapping("{contractId}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<DeliveryNoteResponse> getDeliveryNote(
        @PathVariable("contractId") @NotNull(message = "El ID del contrato no puede ser nulo") Long contractId);

    /**
     * Firma una nota de entrega existente
     * 
     * @param contractId ID del contrato asociado a la nota de entrega
     * @param request Datos de la firma a aplicar
     * @return Respuesta con la nota de entrega firmada
     */
    @PutMapping("{contractId}")
    GenericResponse<DeliveryNoteResponse> signatureDeliveryNote(
            @PathVariable("contractId") @NotNull(message = "El ID del contrato no puede ser nulo") Long contractId,
            @Valid @RequestBody DeliverySignatureRequest request);

}
