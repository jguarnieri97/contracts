package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractInfoResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.ApplicantEntity;
import ar.edu.unlam.tpi.contracts.model.SupplierEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class WorkContractDataHelper {

    public static WorkContractEntity createWorkContractEntity(){
        String codeNumber = ("TEST-" + UUID.randomUUID().toString().substring(0, 4));
        return WorkContractEntity.builder()
                .codeNumber(codeNumber)
                .price(1000.0)
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(30))
                .detail("Test contract")
                .state(WorkStateEnum.PENDING)
                .supplierEntity(SupplierEntity.builder().id(TestUtils.SUPPLIER_ID).build())
                .applicantEntity(ApplicantEntity.builder().id(TestUtils.APPLICANT_ID).build())
                .workers(List.of(3L, 4L, 5L))
                .build();
    }
    public static WorkContractRequest createWorkContractRequest() {
        return WorkContractRequest.builder()
                .price(150000.0)
                .dateFrom(LocalDate.of(2025, 5, 13))
                .dateTo(LocalDate.of(2025, 5, 15))
                .detail("Trabajo de prueba")
                .supplierId(1L)
                .applicantId(2L)
                .workers(List.of(3L, 4L, 5L))
                .build();
    }

    public static WorkContractResponse createWorkContractResponse() {
        return WorkContractResponse.builder()
                .id(1L)
                .price(150000.0)
                .dateFrom(LocalDate.of(2025, 5, 13))
                .dateTo(LocalDate.of(2025, 5, 15))
                .state(WorkStateEnum.PENDING.name())
                .detail("Trabajo de prueba")
                .supplierId(1L)
                .applicantId(2L)
                .build();
    }

    public static WorkContractInfoResponse createWorkContractInfoResponse() {
        return WorkContractInfoResponse.builder()
                .id(1L)
                .codeNumber("CODE123")
                .price(150000.0)
                .dateFrom(LocalDate.of(2025, 5, 13))
                .dateTo(LocalDate.of(2025, 5, 15))
                .state(WorkStateEnum.PENDING.name())
                .detail("Trabajo de prueba")
                .supplierId(1L)
                .applicantId(2L)
                .build();
    }

    public static WorkContractUpdateRequest createWorkContractUpdateRequest() {
        return WorkContractUpdateRequest.builder()
                .state("FINALIZED")
                .build();
    }

}
