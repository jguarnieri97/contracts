package ar.edu.unlam.tpi.contracts.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterRequest {

    private Long applicantId;
    private Long supplierId;
    private List<String> images;

}
