package ar.edu.unlam.tpi.contracts.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BodyData {
    private String noteNumber;
    private List<DescriptionObject> descriptionData;
}
