package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.request.UpdateItemsRequest;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar los contratos de trabajo
 */
@RequestMapping("/contracts/v1")
@Validated
public interface WorkContractController {

    /**
     * Crea un nuevo contrato de trabajo
     * 
     * @param request Datos del contrato a crear
     * @return Respuesta con el contrato creado
     */
    @PostMapping("/work-contract")
    @ResponseStatus(HttpStatus.CREATED)
    GenericResponse<WorkContractResponse> createContract(
        @Valid
        @RequestBody WorkContractRequest request);

    /**
     * Actualiza el estado de un contrato de trabajo existente
     * 
     * @param id ID del contrato a actualizar
     * @param request Datos de actualización del contrato
     * @return Respuesta de confirmación
     */
    @PutMapping("/work-contract/{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> updateContractState(
        @PathVariable @NotNull Long id, 
        @Valid 
        @RequestBody WorkContractUpdateRequest request);

    /**
     * Obtiene un contrato de trabajo por su ID
     * 
     * @param id ID del contrato a obtener
     * @return Respuesta con los datos del contrato
     */
    @GetMapping("/work-contract/{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<WorkContractResponse> getContractById(
        @PathVariable @NotNull Long id);

    /**
     * Actualiza los items de un contrato de trabajo
     * 
     * @param id ID del contrato a actualizar
     * @param request Datos de los items a actualizar
     * @return Respuesta de confirmación
     */
    @PutMapping("/work-contract/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> updateItems(
            @PathVariable @NotNull Long id,
            @Valid
            @RequestBody UpdateItemsRequest request);
   
}