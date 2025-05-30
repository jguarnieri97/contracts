package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractValidator {

    public void validateContractsExist(List<WorkContractEntity> contracts, String field, Long id) {
        if (contracts.isEmpty()) {
            throw new ContractNotFoundException("No se encontraron contratos para el " + field + ": " + id);
        }
    }

}
