package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.request.UpdateItemsRequest;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/contracts/v1")
@Validated
public interface WorkContractController {

    
    @PostMapping("/work-contract")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    GenericResponse<WorkContractResponse> createContract(
        @Valid
        @RequestBody WorkContractRequest request);

    @PutMapping("/work-contract/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    GenericResponse<Void> updateContractState(
        @PathVariable Long id, 
        @Valid 
        @RequestBody WorkContractUpdateRequest request);

    @GetMapping("/work-contract/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    GenericResponse<WorkContractResponse> getContractById(
        @PathVariable Long id);

    @PutMapping("/work-contract/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<Void> updateItems(
            @PathVariable Long id,
            @Valid
            @RequestBody UpdateItemsRequest request);
   
}