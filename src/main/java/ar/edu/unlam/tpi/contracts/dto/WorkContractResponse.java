package ar.edu.unlam.tpi.contracts.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class WorkContractResponse {

    private Long id;
    private Double price;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String detail;
    private String state;   
    private Long supplierId;
    private Long applicantId;
    private List<Long> workers;
}