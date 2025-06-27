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

    @NotNull(message = "El campo 'price' es obligatorio")
    private Double price;

    @NotNull(message = "El campo 'dateFrom' es obligatorio")
    private LocalDate dateFrom;

    @NotNull(message = "El campo 'dateTo' es obligatorio")
    private LocalDate dateTo;

    @NotNull(message = "El campo 'detail' es obligatorio")
    private String detail;

    @NotNull(message = "El campo 'supplierId' es obligatorio")
    private Long supplierId;

    @NotNull(message = "El campo 'applicantId' es obligatorio")
    private Long applicantId;

    @NotNull(message = "El campo 'workers' es obligatorio")
    @Size(min = 1)
    private List<Long> workers;
}