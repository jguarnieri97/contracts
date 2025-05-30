package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.ImageEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;

import java.util.List;

import ar.edu.unlam.tpi.contracts.util.WorkContractConverter;
import ar.edu.unlam.tpi.contracts.util.WorkContractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkContractServiceImpl implements WorkContractService {

    private final WorkContractRepository repository;
    private final WorkContractConverter converter;
    private final WorkContractValidator validator;

    @Override
    public WorkContractResponse createContract(WorkContractRequest request) {
        WorkContractEntity contract = new WorkContractEntity(
                request.getPrice(),
                request.getDateFrom(),
                request.getDateTo(),
                WorkStateEnum.PENDING,
                request.getDetail(),
                request.getSupplierId(),
                request.getApplicantId(),
                request.getWorkers()
        );

        WorkContractEntity saved = repository.save(contract);
        return converter.convertToResponse(saved);
    }

    @Override
    public void updateContractState(Long id, WorkContractUpdateRequest request) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));

        validator.validateStateTransition(contract, request);

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<ImageEntity> images = request.getFiles().stream()
                    .map(base64 -> new ImageEntity(java.util.Base64.getDecoder().decode(base64)))
                    .toList();
            contract.getFiles().addAll(images);
            contract.setState(WorkStateEnum.FINALIZED);
        }

        if (request.getDetail() != null) {
            contract.setDetail(request.getDetail());
        }

        repository.save(contract);
    }

    @Override
    public WorkContractResponse getContractById(Long id) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));
        return converter.convertToResponse(contract);
    }

}
