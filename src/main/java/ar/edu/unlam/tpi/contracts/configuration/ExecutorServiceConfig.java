package ar.edu.unlam.tpi.contracts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutorServiceConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(3);
    }

}
