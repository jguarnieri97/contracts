package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.response.TaskDto;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.TaskEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkContractConverter {

    private final ModelMapper modelMapper = new ModelMapper();

    public WorkContractResponse convertToResponse(WorkContractEntity entity) {
        List<String> base64Images = entity.getFiles() != null
                ? entity.getFiles().stream()
                .map(img -> Base64.getEncoder().encodeToString(img.getData()))
                .toList()
                : List.of();

        return WorkContractResponse.builder()
                .id(entity.getId())
                .codeNumber(entity.getCodeNumber())
                .price(entity.getPrice())
                .dateFrom(entity.getDateFrom())
                .dateTo(entity.getDateTo())
                .state(entity.getState().name())
                .detail(entity.getDetail())
                .supplierId(entity.getSupplierEntity().getId())
                .applicantId(entity.getApplicantEntity().getId())
                .applicantName(entity.getApplicantEntity() != null && entity.getApplicantEntity().getName() != null
                        ? entity.getApplicantEntity().getName()
                        : null)
                .supplierName(entity.getSupplierEntity() != null && entity.getSupplierEntity().getName() != null
                        ? entity.getSupplierEntity().getName()
                        : null)
                .files(base64Images)
                .workers(entity.getWorkers())
                .tasks(entity.getTasks() == null ? null : convertToTaskDto(entity.getTasks()))
                .build();
    }

    private List<TaskDto> convertToTaskDto(List<TaskEntity> tasks) {
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public WorkContractInfoResponse convertToInfoResponse(WorkContractEntity entity) {
        return WorkContractInfoResponse.builder()
                .id(entity.getId())
                .codeNumber(entity.getCodeNumber())
                .price(entity.getPrice())
                .dateFrom(entity.getDateFrom())
                .dateTo(entity.getDateTo())
                .state(entity.getState().name())
                .detail(entity.getDetail())
                .supplierId(entity.getSupplierEntity().getId())
                .supplierName(entity.getSupplierEntity().getName())
                .applicantId(entity.getApplicantEntity().getId())
                .applicantName(entity.getApplicantEntity().getName())
                .build();
    }

}
