package ar.edu.unlam.tpi.contracts.client.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.client.error.ErrorHandler;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.response.BlockchainResponse;
import ar.edu.unlam.tpi.contracts.dto.response.ErrorResponse;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.exception.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockchainServiceClientImpl implements BlockchainServiceClient {

    private final WebClient webClient;
    private final ErrorHandler errorHandler;

    @Value("${blockchain.service.host}")
    private String host;

    @Override
    public BlockchainResponse certificateFile(BlockchainRequest request) {
        log.info("Enviando solicitud de certificado al servicio blockchain: {}", request);
        var response = webClient.post()
                .uri(host + "delivery-note")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(Mono.just(request), BlockchainRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(errorHandler::handle4xxError))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(errorHandler::handle5xxError))
                .bodyToMono(new ParameterizedTypeReference<GenericResponse<BlockchainResponse>>() {})
                .doOnError(errorHandler::onClientError)
                .block();

        if (response == null || response.getData() == null) {
            throw new ClientException("La respuesta del servidor es nula o inválida");
        }
        return response.getData();
    }

    @Override
    public void verifyCertificate(BlockchainVerifyRequest request) {
        log.info("Enviando solicitud de verificación de certificado al servicio blockchain: {}", request);
        webClient.post()
                .uri(host + "delivery-note/verify")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(Mono.just(request), BlockchainVerifyRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(errorHandler::handle4xxError))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(errorHandler::handle5xxError))
                .bodyToMono(Void.class)
                .doOnError(errorHandler::onClientError)
                .timeout(Duration.ofSeconds(30))
                .block();
    }

}
