package ar.edu.unlam.tpi.contracts.service;

/**
 * Interfaz para generar números de código únicos para contratos de trabajo.
 */
public interface CodeNumberGenerator {

    /**
     * Genera un número de código único para un contrato de trabajo.
     */
    String generateCodeNumber();
}
