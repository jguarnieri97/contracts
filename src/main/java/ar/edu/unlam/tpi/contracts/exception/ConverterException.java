package ar.edu.unlam.tpi.contracts.exception;

import static ar.edu.unlam.tpi.contracts.util.Constants.INTERNAL_ERROR;
import static ar.edu.unlam.tpi.contracts.util.Constants.STATUS_INTERNAL;

public class ConverterException extends GenericException {


    public ConverterException(String detail) {
        super(STATUS_INTERNAL, INTERNAL_ERROR, detail);
    }
}
