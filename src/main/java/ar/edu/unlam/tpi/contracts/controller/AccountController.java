package ar.edu.unlam.tpi.contracts.controller;

import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * Controlador para gestionar las consultas de contratos por cuentas
 */
@RequestMapping("/contracts/v1")
@Validated
public interface AccountController {

    /**
     * Obtiene los contratos asociados a un solicitante (applicant)
     *
     * @param applicantId ID del solicitante
     * @param limit Si se debe limitar la cantidad de resultados
     * @return Lista de contratos asociados al solicitante
     */
    @GetMapping("/accounts/applicant/{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsByApplicantId(
            @PathVariable("id") @NotNull(message = "El ID del solicitante no puede ser nulo") Long applicantId,
            @RequestParam(required = false) Boolean limit);

    /**
     * Obtiene los contratos asociados a un proveedor (supplier)
     *
     * @param id ID del proveedor
     * @param limit Si se debe limitar la cantidad de resultados
     * @return Lista de contratos asociados al proveedor
     */
    @GetMapping("/accounts/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsBySupplierId(
            @PathVariable @NotNull(message = "El ID del proveedor no puede ser nulo") Long id,
            @RequestParam(required = false) Boolean limit);

    /**
     * Obtiene los contratos asociados a un trabajador (worker)
     *
     * @param id ID del trabajador
     * @param range Rango de tiempo para filtrar los contratos (por defecto "week")
     * @return Lista de contratos asociados al trabajador
     */
    @GetMapping("/accounts/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<List<WorkContractInfoResponse>> getContractsByWorkerId(
            @PathVariable @NotNull(message = "El ID del trabajador no puede ser nulo") Long id,
            @RequestParam(defaultValue = "week") String range);
}
