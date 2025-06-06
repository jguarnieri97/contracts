package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.ImageEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.CodeNumberGenerator;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;

import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import ar.edu.unlam.tpi.contracts.util.WorkContractValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkContractServiceImpl implements WorkContractService {

    private final WorkContractDAO repository;
    private final WorkContractConverter converter;
    private final WorkContractValidator validator;
    private final CodeNumberGenerator codeNumberGenerator;

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
        if (!WorkStateEnum.FINALIZED.name().equalsIgnoreCase(request.getState())) {
            validator.validateStateFinalized(request);
        }
        WorkContractEntity contract = repository.findById(id);
        contract.setState(WorkStateEnum.valueOf(request.getState()));
        contract.setDetail(request.getDetail() == null ? contract.getDetail() : request.getDetail());
        if (request.getFiles() != null) {
            addImagesToContract(contract, request.getFiles());
        }
        repository.save(contract);
    }

    @Override
    public WorkContractResponse getContractById(Long id) {
        WorkContractEntity contract = repository.findById(id);
        return converter.convertToResponse(contract);
    }

    private void addImagesToContract(WorkContractEntity contract, List<String> filesBase64) {
        List<ImageEntity> images = filesBase64.stream()
                .map(base64 -> new ImageEntity(Base64.getDecoder().decode(base64)))
                .toList();
        if (contract.getFiles() == null) {
            contract.setFiles(new ArrayList<>());
        }
        contract.getFiles().addAll(images);
    }

}
