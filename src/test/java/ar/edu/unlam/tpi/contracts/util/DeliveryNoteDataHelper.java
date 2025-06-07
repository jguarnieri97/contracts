package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.CompanyData;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;


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

}
