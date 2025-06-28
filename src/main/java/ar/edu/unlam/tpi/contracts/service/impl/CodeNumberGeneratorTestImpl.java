package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.service.CodeNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
@Slf4j
public class CodeNumberGeneratorTestImpl implements CodeNumberGenerator {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String generateCodeNumber() {
        log.info("Generando el siguiente valor de secuencia");
        String sql = "SELECT nextval('work_contract_sequence')";
        Long nextValue = jdbcTemplate.queryForObject(sql, Long.class);
        if (nextValue == null) {
            throw new RuntimeException("No se pudo obtener el siguiente valor de la secuencia");
        }
        return String.valueOf(nextValue);
    }
}
