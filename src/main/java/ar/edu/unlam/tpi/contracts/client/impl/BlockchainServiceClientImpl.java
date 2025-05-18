package ar.edu.unlam.tpi.contracts.client.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.dto.BlockchainResponse;
import ar.edu.unlam.tpi.contracts.dto.ErrorResponse;
import ar.edu.unlam.tpi.contracts.dto.GenericResponse;
import ar.edu.unlam.tpi.contracts.exception.BlockchainClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockchainServiceClientImpl implements BlockchainServiceClient {

    private final WebClient webClient;

    @Value("${blockchain.service.host}")
    private String host;

    @Override
    public BlockchainResponse certificateFile(BlockchainRequest request) {
        var response = webClient.post()
                .uri(host + "budget")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(Mono.just(request), BlockchainRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(BlockchainServiceClientImpl::handle4xxError))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class)
                                .flatMap(BlockchainServiceClientImpl::handle5xxError))
                .bodyToMono(new ParameterizedTypeReference<GenericResponse<BlockchainResponse>>() {})
                .onErrorComplete(BlockchainServiceClientImpl::onClientError)
                .block();

        assert response != null;
        return response.getData();
    }

    private static boolean onClientError(Throwable e) {
        log.error("Error al ejecutar el request: {}", e.getMessage());
        throw new BlockchainClientException(e.getMessage());
    }

    private static Mono<Throwable> handle4xxError(ErrorResponse error) {
        log.error("Error del cliente externo Budgets API: {}", error);
        return Mono.error(new BlockchainClientException(error));
    }

    private static Mono<Throwable> handle5xxError(ErrorResponse error) {
        log.error("Error del servidor externo Budgets API: {}", error);
        return Mono.error(new BlockchainClientException(error));
    }
}
