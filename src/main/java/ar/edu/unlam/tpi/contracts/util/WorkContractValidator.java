package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import org.springframework.stereotype.Component;

@Component
public class WorkContractValidator {

    public void validateStateTransition(WorkContractEntity contract, WorkContractUpdateRequest request) {
        WorkState newState = WorkState.valueOf(request.getState().toUpperCase());

        if (newState == contract.getState()) {
            throw new IllegalArgumentException("El contrato ya tiene ese estado.");
        }

        if (newState != WorkState.FINALIZED && request.getFiles() != null && !request.getFiles().isEmpty()) {
            throw new IllegalArgumentException("Las imágenes solo pueden ser cargadas cuando el estado es FINALIZED");
        }
    }

}
