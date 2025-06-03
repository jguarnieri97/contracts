package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import org.springframework.stereotype.Component;

@Component
public class WorkContractValidator {

    public void validateStateFinalized(WorkContractEntity contract, WorkContractUpdateRequest request) {
        WorkStateEnum newState = WorkStateEnum.valueOf(request.getState().toUpperCase());

        if (newState == contract.getState()) {
            throw new IllegalArgumentException("El contrato ya tiene ese estado.");
        }

        if (newState != WorkStateEnum.FINALIZED && request.getFiles() != null && !request.getFiles().isEmpty()) {
            throw new IllegalArgumentException("Las imágenes solo pueden ser cargadas cuando el estado es FINALIZED");
        }
    }

}
