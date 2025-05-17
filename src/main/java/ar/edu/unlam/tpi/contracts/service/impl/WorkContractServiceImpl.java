package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.ImageEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.service.WorkContractService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WorkContractServiceImpl implements WorkContractService {

    private final WorkContractRepository repository;

    public WorkContractServiceImpl(WorkContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkContractResponse createContract(WorkContractRequest request) {
        WorkContractEntity contract = new WorkContractEntity(
                request.getPrice(),
                request.getDateFrom(),
                request.getDateTo(),
                WorkState.PENDING, 
                request.getDetail(),
                request.getSupplierId(),
                request.getApplicantId(),
                request.getWorkers()
        );

       
        WorkContractEntity saved = repository.save(contract);

       
        return convertToResponse(saved);
    }
    
    @Override
    public void updateContractState(Long id, WorkContractUpdateRequest request) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));
    
        try {
            WorkState newState = WorkState.valueOf(request.getState().toUpperCase());
    
            if (newState == contract.getState()) {
                throw new IllegalArgumentException("El contrato ya tiene ese estado.");
            }
    
            contract.setState(newState);
    
            if (newState == WorkState.FINALIZED) {
                if (request.getFiles() != null && !request.getFiles().isEmpty()) {
                    List<ImageEntity> images = request.getFiles().stream()
                            .map(base64 -> new ImageEntity(java.util.Base64.getDecoder().decode(base64)))
                            .toList();
        
                    contract.getFiles().addAll(images);
                }
                if (request.getDetail() != null) {
                    contract.setDetail(request.getDetail());
                }
            } else if (request.getFiles() != null && !request.getFiles().isEmpty()) {
                throw new IllegalArgumentException("Las imágenes solo pueden ser cargadas cuando el estado es FINALIZED");
            }
    
            repository.save(contract);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido: " + request.getState());
        }
    }
    

    @Override
    public WorkContractResponse getContractById(Long id) {
        WorkContractEntity contract = repository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("No se encontró un contrato con el ID: " + id));
        return convertToResponse(contract);
    }
    

    private WorkContractResponse convertToResponse(WorkContractEntity entity) { //convierte el entity a response
        List<String> base64Images = entity.getFiles().stream()
        .map(img -> java.util.Base64.getEncoder().encodeToString(img.getData()))
        .toList();

        return WorkContractResponse.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .dateFrom(entity.getDateFrom())
                .dateTo(entity.getDateTo())
                .state(entity.getState().name())
                .detail(entity.getDetail())
                .supplierId(entity.getSupplierId())
                .applicantId(entity.getApplicantId())
                .files(base64Images)
                .workers(entity.getWorkers())
                .build();
    }
    



}
