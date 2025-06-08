package ar.edu.unlam.tpi.contracts.client.error;

import ar.edu.unlam.tpi.contracts.dto.response.ErrorResponse;
import ar.edu.unlam.tpi.contracts.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ErrorHandler {

    public Mono<Throwable> handle4xxError(ErrorResponse error) {
        log.error("Error del cliente externo: {}", error);
        return Mono.error(new ClientException(error));
    }

    public Mono<Throwable> handle5xxError(ErrorResponse error) {
        log.error("Error del servidor externo: {}", error);
        return Mono.error(new ClientException(error));
    }

    public boolean onClientError(Throwable e) {
        log.error("Error al ejecutar el request: {}", e.getMessage());
        throw new ClientException(e.getMessage());
    }

}
