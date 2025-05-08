package ar.edu.unlam.tpi.contracts.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WorkContractResponse {

    private Long id;
    private Double price;
    private String dateFrom;
    private String dateTo;
    private String detail;
    private String state;   
    private Long supplierId;
    private Long applicantId;
    private List<Long> workers;
}