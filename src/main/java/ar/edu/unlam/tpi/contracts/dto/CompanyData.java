package ar.edu.unlam.tpi.contracts.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyData {

    private String companyName;
    private String cuit;

}
