package ar.edu.unlam.tpi.contracts.client.impl;

import ar.edu.unlam.tpi.contracts.client.ValidationClient;
import ar.edu.unlam.tpi.contracts.client.error.ErrorHandler;
import ar.edu.unlam.tpi.contracts.dto.request.RegisterRequest;
import ar.edu.unlam.tpi.contracts.dto.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class ValidationClientImpl implements ValidationClient {

    private final WebClient webClient;
    private final ErrorHandler errorHandler;

    @Value("${validation.host}")
    private String host;

    @Override
    public void registerWork(RegisterRequest request) {
        webClient.post()
                .uri(host + "register")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(Mono.just(request), RegisterRequest.class)
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
