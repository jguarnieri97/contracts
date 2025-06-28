package ar.edu.unlam.tpi.contracts.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data 
@Builder
public class UpdateItemsRequest {

    @NotNull(message = "El campo 'tasks' es obligatorio")
    private List<String> tasks;

}