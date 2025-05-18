package ar.edu.unlam.tpi.contracts.exception;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(String message) {
        super(message);
    }
} 