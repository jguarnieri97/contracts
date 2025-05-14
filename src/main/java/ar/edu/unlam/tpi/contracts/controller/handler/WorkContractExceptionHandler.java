package ar.edu.unlam.tpi.contracts.controller.handler;

import ar.edu.unlam.tpi.contracts.dto.GenericResponse;
import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WorkContractExceptionHandler {

    @ExceptionHandler(ContractNotFoundException.class)
    public ResponseEntity<GenericResponse<Void>> handleContractNotFoundException(ContractNotFoundException ex) {
        GenericResponse<Void> response = new GenericResponse<>(
            Constants.STATUS_INTERNAL,
            ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 