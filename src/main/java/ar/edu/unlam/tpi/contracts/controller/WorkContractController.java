package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts/v1")
@Validated
public class WorkContractController {
    private final WorkContractService service;

    public WorkContractController(WorkContractService service) {
        this.service = service;
    }

    @PostMapping("/work-contract")
    public ResponseEntity<WorkContractResponse> createContract(@Valid @RequestBody WorkContractRequest request) {
        WorkContractResponse response = service.createContract(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/work-contract/{id}")
    public ResponseEntity<String> updateContractState(
            @PathVariable Long id,
            @RequestBody @Valid WorkContractUpdateRequest request) {
    
        service.updateContractState(id, request);
        return ResponseEntity.ok("Contrato actualizado correctamente");
    }
    

   
}