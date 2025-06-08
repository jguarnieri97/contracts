package ar.edu.unlam.tpi.contracts.exception;


import ar.edu.unlam.tpi.contracts.dto.response.ErrorResponse;

import static ar.edu.unlam.tpi.contracts.util.Constants.INTERNAL_ERROR;
import static ar.edu.unlam.tpi.contracts.util.Constants.STATUS_INTERNAL;

public class ClientException extends GenericException{
    public ClientException(ErrorResponse error) {
        super(error.getCode(), error.getMessage(), error.getDetail());
    }

    public ClientException(String detail) {
        super(STATUS_INTERNAL, INTERNAL_ERROR, detail);
    }
}
