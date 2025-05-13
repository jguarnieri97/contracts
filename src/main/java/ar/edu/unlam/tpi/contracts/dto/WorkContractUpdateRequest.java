package ar.edu.unlam.tpi.contracts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkContractUpdateRequest {

    @NotNull(message = "El estado no puede ser nulo")
    private String state;
}
