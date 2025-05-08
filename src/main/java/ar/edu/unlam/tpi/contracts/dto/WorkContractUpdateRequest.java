package ar.edu.unlam.tpi.contracts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkContractUpdateRequest {

    @NotNull(message = "El estado no puede ser nulo")
    private String state;
}
