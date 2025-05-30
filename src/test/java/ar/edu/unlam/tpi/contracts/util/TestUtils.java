package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.*;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;

public class TestUtils {

    public static final long CONTRACT_ID = 1L;
    public static final long SUPPLIER_ID = 1L;
    public static final long APPLICANT_ID = 2L;
    public static final String DATE_FROM = "2025-05-13";
    public static final String DATE_TO = "2025-05-15";
    public static final String APPLICANT_COMPANY = "Applicant Company";
    public static final String CUIT = "20-12345678-9";
    public static final String SUPPLIER_COMPANY = "Supplier Company";
    public static final String NOTE_NUMBER = "123456";

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

    public static DeliveryNoteRequest buildDeliveryNoteRequest() {
        return DeliveryNoteRequest.builder()
                .contractId(CONTRACT_ID)
                .applicantData(CompanyData.builder()
                        .companyName(APPLICANT_COMPANY)
                        .cuit(CUIT)
                        .build())
                .supplierData(CompanyData.builder()
                        .companyName(SUPPLIER_COMPANY)
                        .cuit(CUIT)
                        .build())
                .bodyData(BodyData.builder()
                        .noteNumber(NOTE_NUMBER)
                        .descriptionData(List.of())
                        .build())
                .footData(FootData.builder().build())
                .build();
    }
    
}
