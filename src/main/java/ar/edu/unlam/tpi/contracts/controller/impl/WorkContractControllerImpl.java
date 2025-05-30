package ar.edu.unlam.tpi.contracts.controller.impl;


import ar.edu.unlam.tpi.contracts.controller.WorkContractController;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import ar.edu.unlam.tpi.contracts.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkContractControllerImpl implements WorkContractController {

    private final WorkContractService service;

    @Override
    public GenericResponse<WorkContractResponse> createContract(WorkContractRequest request) {
        WorkContractResponse response = service.createContract(request);
        return new GenericResponse<>(
                Constants.STATUS_CREATED,
                Constants.CREATED_MESSAGE,
                response
        );
    }

    @Override
    public GenericResponse<Void> updateContractState(Long id, WorkContractUpdateRequest request) {
        service.updateContractState(id, request);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.UPDATED_MESSAGE,
                null
        );
    }

    @Override
    public GenericResponse<WorkContractResponse> getContractById(Long id) {
        WorkContractResponse response = service.getContractById(id);
        return new GenericResponse<>(
                Constants.STATUS_OK,
                Constants.SUCCESS_MESSAGE,
                response
        );
    }
}

