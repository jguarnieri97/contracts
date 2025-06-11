package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contracts/v1")
@Validated
public interface AccountController {

    @GetMapping("/accounts/applicant/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsByApplicantId(
            @PathVariable("id") @NotNull Long applicantId,
            @RequestParam(required = false) Boolean limit);

    @GetMapping("/accounts/supplier/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsBySupplierId(
            @PathVariable @NotNull Long id,
            @RequestParam(required = false) Boolean limit);

    @GetMapping("/accounts/worker/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsByWorkerId(
            @PathVariable @NotNull Long id,
            @RequestParam(defaultValue = "week") String range);
}
