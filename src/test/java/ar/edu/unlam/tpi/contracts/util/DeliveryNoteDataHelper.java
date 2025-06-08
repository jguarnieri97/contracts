package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.CompanyData;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;

import java.time.LocalDateTime;


public class DeliveryNoteDataHelper {

    public static DeliveryNoteRequest createDeliveryNoteRequest() {
        return DeliveryNoteRequest.builder()
                .contractId(1L)
                .applicantData(CompanyData.builder()
                        .companyName("Applicant Company")
                        .cuit("20-12345678-9")
                        .build())
                .supplierData(CompanyData.builder()
                        .companyName("Supplier Company")
                        .cuit("20-12345678-9")
                        .build())
                .build();
    }

    public static DeliveryNote createDeliveryNote() {
        return DeliveryNote.builder()
                .id(1L)
                .data(new byte[]{})
                .isSigned(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
