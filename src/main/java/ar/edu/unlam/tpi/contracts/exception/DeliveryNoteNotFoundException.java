package ar.edu.unlam.tpi.contracts.exception;

import static ar.edu.unlam.tpi.contracts.util.Constants.NOT_FOUND_ERROR;
import static ar.edu.unlam.tpi.contracts.util.Constants.STATUS_NOT_FOUND;

public class DeliveryNoteNotFoundException extends GenericException {
    public DeliveryNoteNotFoundException(String detail) {
        super(STATUS_NOT_FOUND, NOT_FOUND_ERROR, detail);
    }
}
