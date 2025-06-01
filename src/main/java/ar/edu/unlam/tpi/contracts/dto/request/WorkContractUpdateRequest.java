package ar.edu.unlam.tpi.contracts.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkContractUpdateRequest {

    @NotNull(message = "El estado no puede ser nulo")
    private String state;

    private String detail;

    private List<String> files;

}
