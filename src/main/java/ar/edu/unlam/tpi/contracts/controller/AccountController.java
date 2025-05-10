package ar.edu.unlam.tpi.contracts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import java.util.List;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.service.AccountService;

@RestController
@RequestMapping("/contracts/v1")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/accounts/applicant/{id}")
    public ResponseEntity<List<WorkContractResponse>> getContractsByApplicantId(
            @PathVariable("id") Long applicantId,
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(service.getContractsByApplicantId(applicantId, limit));
    }

    @GetMapping("/accounts/supplier/{id}")
    public ResponseEntity<List<WorkContractResponse>> getContractsBySupplierId(
        @PathVariable Long id,
        @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(service.getContractsBySupplierId(id, limit));
    }

    @GetMapping("/accounts/worker/{id}")
    public ResponseEntity<List<WorkContractResponse>> getContractsByWorkerId(
        @PathVariable Long id) {
        return ResponseEntity.ok(service.getContractsByWorkerId(id));
    }
}
