package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;

public class TestUtils {

    public static final long CONTRACT_ID = 1L;
    public static final long SUPPLIER_ID = 1L;
    public static final long APPLICANT_ID = 2L;
    public static final String DATE_FROM = "2025-05-13";
    public static final String DATE_TO = "2025-05-15";

    public static WorkContractRequest buildWorkContractRequest() {
        return WorkContractRequest.builder()
                .price(150000.0)
                .dateFrom(LocalDate.parse(DATE_FROM))
                .dateTo(LocalDate.parse(DATE_TO))
                .detail("Trabajo de prueba")
                .supplierId(SUPPLIER_ID)
                .applicantId(APPLICANT_ID)
                .workers(List.of(3L, 4L, 5L))
                .build();
    }

    public static WorkContractResponse buildWorkContractResponse() {
        return WorkContractResponse.builder()
                .id(CONTRACT_ID)
                .price(150000.0)
                .dateFrom(LocalDate.parse(DATE_FROM))
                .dateTo(LocalDate.parse(DATE_TO))
                .state(WorkState.PENDING.name())
                .detail("Trabajo de prueba")
                .supplierId(SUPPLIER_ID)
                .applicantId(APPLICANT_ID)
                .build();
    }

    public static WorkContractUpdateRequest buildWorkContractUpdateRequest() {
        return WorkContractUpdateRequest.builder()
                .state("FINALIZED")
                .build();
    }
    
}
