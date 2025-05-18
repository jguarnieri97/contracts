package ar.edu.unlam.tpi.contracts.dto;

import lombok.Data;

import java.util.List;

@Data
public class BodyData {
    private String noteNumber;
    private List<DescriptionObject> descriptionData;
}
