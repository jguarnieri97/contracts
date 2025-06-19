package ar.edu.unlam.tpi.contracts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliverySignatureRequest {
    private String signature;
    private String clarification;
}
