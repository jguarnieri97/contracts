package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.ValidationClient;
import ar.edu.unlam.tpi.contracts.dto.request.RegisterRequest;
import ar.edu.unlam.tpi.contracts.dto.request.UpdateItemsRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.*;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.CodeNumberGenerator;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;

import ar.edu.unlam.tpi.contracts.util.Converter;
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
    private final ValidationClient validationClient;

    @Override
    public WorkContractResponse createContract(WorkContractRequest request) {
        log.info("Creando contrato de trabajo con solicitud: {}", request);
        WorkContractEntity contract = WorkContractEntity.builder()
                .codeNumber(codeNumberGenerator.generateCodeNumber())
                .price(request.getPrice())
                .dateFrom(request.getDateFrom())
                .dateTo(request.getDateTo())
                .state(WorkStateEnum.PENDING)
                .detail(request.getDetail())
                .supplierEntity(SupplierEntity.builder().id(request.getSupplierId()).build())
                .applicantEntity(ApplicantEntity.builder().id(request.getApplicantId()).build())
                .workers(request.getWorkers())
                .build();

        WorkContractEntity saved = repository.save(contract);
        return converter.convertToResponse(saved);
    }

    @Override
    public void updateContractState(Long id, WorkContractUpdateRequest request) {
        log.info("Actualizando estado del contrato de trabajo ID: {} con solicitud: {}", id, request);
        if (!WorkStateEnum.FINALIZED.name().equalsIgnoreCase(request.getState())) {
            validator.validateStateFinalized(request);
        }
        WorkContractEntity contract = repository.findById(id);
        contract.setState(WorkStateEnum.valueOf(request.getState()));
        contract.setDetail(request.getDetail() == null ? contract.getDetail() : request.getDetail());
        if (request.getFiles() != null) {
            addImagesToContract(contract, request.getFiles());
        }

        log.info("Guardando contrato de trabajo actualizado: {}", contract);
        repository.save(contract);

        if (WorkStateEnum.FINALIZED.name().equalsIgnoreCase(request.getState())) {
            RegisterRequest registerRequest = Converter.toRegisterRequest(contract, request.getFiles());
            validationClient.registerWork(registerRequest);
        }
    }

    @Override
    public WorkContractResponse getContractById(Long id) {
        log.info("Recuperando contrato de trabajo por ID: {}", id);
        WorkContractEntity contract = repository.findById(id);
        return converter.convertToResponse(contract);
    }

    @Override
    public void updateTasks(Long id, UpdateItemsRequest request) {
        log.info("Actualizando tareas del contrato de trabajo ID: {} con solicitud: {}", id, request);
        WorkContractEntity contract = repository.findById(id);

        for (String description : request.getTasks()) {
            TaskEntity newTask = new TaskEntity();
            newTask.setDescription(description);
            newTask.setWorkContract(contract);
            contract.getTasks().add(newTask);
        }

        log.info("Guardando contrato de trabajo actualizado con nuevas tareas: {}", contract);
        repository.save(contract);
    }


    private void addImagesToContract(WorkContractEntity contract, List<String> filesBase64) {
        log.info("Agregando imágenes al contrato de trabajo ID: {} con archivos: {}", contract.getId(), filesBase64);
        List<ImageEntity> images = filesBase64.stream()
                .map(base64 -> new ImageEntity(Base64.getDecoder().decode(base64), contract))
                .toList();
        if (contract.getFiles() == null) {
            contract.setFiles(new ArrayList<>());
        }
        contract.getFiles().addAll(images);
    }

}
