package ar.edu.unlam.tpi.contracts.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliverySignatureRequest {

    @NotNull(message = "El campo 'signature' es obligatorio")
    private String signature;
    
    @NotNull(message = "El campo 'clarification' es obligatorio")
    private String clarification;
}
