package ar.edu.unlam.tpi.contracts.configuration;

import ar.edu.unlam.tpi.contracts.service.CodeNumberGenerator;
import ar.edu.unlam.tpi.contracts.service.impl.CodeNumberGeneratorProdImpl;
import ar.edu.unlam.tpi.contracts.service.impl.CodeNumberGeneratorTestImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class CodeNumberConfig {

    @Value("${app.start.mode}")
    private String mode;

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public CodeNumberGenerator getCodeNumberGenerator() {
        if (mode.equals("test"))
            return new CodeNumberGeneratorTestImpl(jdbcTemplate);
        else
            return new CodeNumberGeneratorProdImpl(jdbcTemplate);
    }
}
