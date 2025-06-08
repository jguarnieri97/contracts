package ar.edu.unlam.tpi.contracts.controller.impl;

import ar.edu.unlam.tpi.contracts.controller.AccountController;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
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
    public GenericResponse<List<WorkContractInfoResponse>> getContractsByApplicantId(Long applicantId, Boolean limit) {
        List<WorkContractInfoResponse> contracts = service.getContractsByApplicantId(applicantId, limit);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.SUCCESS_MESSAGE,
                contracts);
    }

    @Override
    public GenericResponse<List<WorkContractInfoResponse>> getContractsBySupplierId(Long id, Boolean limit) {
        List<WorkContractInfoResponse> contracts = service.getContractsBySupplierId(id, limit);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.SUCCESS_MESSAGE,
                contracts);
    }

    @Override
    public GenericResponse<List<WorkContractInfoResponse>> getContractsByWorkerId(Long id, String range) {
        List<WorkContractInfoResponse> contracts = service.getContractsByWorkerId(id, range);
        return new GenericResponse<>(
                Constants.STATUS_OK, 
                Constants.SUCCESS_MESSAGE, 
                contracts);
    }
}