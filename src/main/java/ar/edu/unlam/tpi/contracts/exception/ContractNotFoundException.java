package ar.edu.unlam.tpi.contracts.exception;

import static ar.edu.unlam.tpi.contracts.util.Constants.STATUS_NOT_FOUND;
import static ar.edu.unlam.tpi.contracts.util.Constants.NOT_FOUND_ERROR;

public class ContractNotFoundException extends GenericException {
    public ContractNotFoundException(String detail) {
        super(STATUS_NOT_FOUND, NOT_FOUND_ERROR, detail);
    }
} 