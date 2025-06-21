package ar.edu.unlam.tpi.contracts.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data 
@Builder
public class UpdateItemsRequest {

    @NotNull
    private List<String> tasks;

}