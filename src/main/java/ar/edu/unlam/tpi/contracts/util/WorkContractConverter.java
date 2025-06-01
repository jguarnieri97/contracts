package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class WorkContractConverter {

    public WorkContractResponse convertToResponse(WorkContractEntity entity) {
        List<String> base64Images = entity.getFiles().stream()
                .map(img -> Base64.getEncoder().encodeToString(img.getData()))
                .toList();

        return WorkContractResponse.builder()
                .id(entity.getId())
                .codeNumber(entity.getCodeNumber())
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
