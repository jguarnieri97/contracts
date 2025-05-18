package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.AccountController;
import ar.edu.unlam.tpi.contracts.dto.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.service.AccountService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService service;

    @Override
    public GenericResponse<List<WorkContractResponse>> getContractsByApplicantId(Long applicantId, Boolean limit) {
        var contracts = service.getContractsByApplicantId(applicantId, limit);
        return new GenericResponse<>(
            Constants.STATUS_OK, 
            Constants.SUCCESS_MESSAGE, 
            contracts);
    }

    @Override
    public GenericResponse<List<WorkContractResponse>> getContractsBySupplierId(Long id, Boolean limit) {
        var contracts = service.getContractsBySupplierId(id, limit);
        return new GenericResponse<>(
            Constants.STATUS_OK, 
            Constants.SUCCESS_MESSAGE, 
            contracts);
    }

    @Override
    public GenericResponse<List<WorkContractResponse>> getContractsByWorkerId(Long id) {
        var contracts = service.getContractsByWorkerId(id);
        return new GenericResponse<>(
            Constants.STATUS_OK, 
            Constants.SUCCESS_MESSAGE, 
            contracts);
    }
}