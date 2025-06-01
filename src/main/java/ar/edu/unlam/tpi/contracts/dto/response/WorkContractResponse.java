package ar.edu.unlam.tpi.contracts.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class WorkContractResponse {

    private Long id;
    private String codeNumber;
    private Double price;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String detail;
    private String state;   
    private Long supplierId;
    private Long applicantId;
    private List<Long> workers;
    private List<String> files;
}