package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.ImageEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;
import ar.edu.unlam.tpi.contracts.service.CodeNumberGeneratorService;

import java.util.List;

import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import ar.edu.unlam.tpi.contracts.util.WorkContractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkContractServiceImpl implements WorkContractService {

    private final WorkContractDAO repository;
    private final WorkContractConverter converter;
    private final WorkContractValidator validator;
    private final CodeNumberGeneratorService codeNumberGenerator;

    @Override
    public WorkContractResponse createContract(WorkContractRequest request) {
        WorkContractEntity contract = WorkContractEntity.builder()
                .codeNumber(codeNumberGenerator.generateCodeNumber())
                .price(request.getPrice())
                .dateFrom(request.getDateFrom())
                .dateTo(request.getDateTo())
                .state(WorkStateEnum.PENDING)
                .detail(request.getDetail())
                .supplierId(request.getSupplierId())
                .applicantId(request.getApplicantId())
                .workers(request.getWorkers())
                .build();

        WorkContractEntity saved = repository.save(contract);
        return converter.convertToResponse(saved);
    }

    @Override
    public void updateContractState(Long id, WorkContractUpdateRequest request) {
        WorkContractEntity contract = repository.findById(id);
        if (WorkStateEnum.FINALIZED.name().equalsIgnoreCase(request.getState())) {
            validator.validateStateFinalized(contract, request);
        }
        contract.setState(WorkStateEnum.valueOf(request.getState()));
        contract.setDetail(request.getDetail() == null ? null : request.getDetail());
        contract.setFiles(
                request.getFiles() == null ? null :
                        request.getFiles().stream()
                                .map(base64 -> new ImageEntity(java.util.Base64.getDecoder().decode(base64)))
                                .toList()
        );
        repository.save(contract);
    }

    @Override
    public WorkContractResponse getContractById(Long id) {
        WorkContractEntity contract = repository.findById(id);
        return converter.convertToResponse(contract);
    }

}
