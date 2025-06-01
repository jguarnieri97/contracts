package ar.edu.unlam.tpi.contracts.dto.request;

import java.util.List;

import ar.edu.unlam.tpi.contracts.dto.CompanyData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryNoteRequest {

    private Long contractId;
    private List<CompanyData> suppliersData;
    private List<CompanyData> applicantsData;
}
