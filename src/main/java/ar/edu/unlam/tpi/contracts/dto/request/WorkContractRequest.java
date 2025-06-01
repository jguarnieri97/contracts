package ar.edu.unlam.tpi.contracts.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data 
@Builder
public class WorkContractRequest {

    @NotNull
    private Double price;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private String detail;

    @NotNull
    private Long supplierId;

    @NotNull
    private Long applicantId;

    @NotNull
    @Size(min = 1)
    private List<Long> workers;
}