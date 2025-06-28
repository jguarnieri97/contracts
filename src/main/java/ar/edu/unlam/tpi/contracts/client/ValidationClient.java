package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.request.RegisterRequest;

/**
 * Cliente para interactuar con el servicio de validación
 */
public interface ValidationClient {

    /**
     * Registra un trabajo en el servicio de validación
     *
     * @param request Datos de la solicitud de registro
     */
    void registerWork(RegisterRequest request);

}
