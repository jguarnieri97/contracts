package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class WorkContractConverter {

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
                .files(base64Images)
                .workers(entity.getWorkers())
                .build();
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
