package ar.edu.unlam.tpi.contracts.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WorkContractInfoResponse {

    private Long id;
    private String codeNumber;
    private Double price;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String detail;
    private String state;
    private String supplierName;
    private Long supplierId;
    private String applicantName;
    private Long applicantId;
}
