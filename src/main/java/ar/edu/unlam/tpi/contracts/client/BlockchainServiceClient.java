package ar.edu.unlam.tpi.contracts.client;

import ar.edu.unlam.tpi.contracts.dto.request.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.response.BlockchainResponse;

/**
 * Cliente para interactuar con el servicio de Blockchain
 */
public interface BlockchainServiceClient {

    /**
     * Certifica un archivo en la blockchain
     *
     * @param request Datos de la solicitud de certificación
     * @return Respuesta de la blockchain con los datos de la certificación
     */
    BlockchainResponse certificateFile(BlockchainRequest request);

    /**
     * Verifica un certificado en la blockchain
     *
     * @param request Datos de la solicitud de verificación
     */
    void verifyCertificate(BlockchainVerifyRequest request);

}
