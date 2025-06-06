package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.service.CodeNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class CodeNumberGeneratorTestImpl implements CodeNumberGenerator {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String generateCodeNumber() {
        String sql = "SELECT nextval('work_contract_sequence')";
        Long nextValue = jdbcTemplate.queryForObject(sql, Long.class);
        if (nextValue == null) {
            throw new RuntimeException("No se pudo obtener el siguiente valor de la secuencia");
        }
        return String.valueOf(nextValue);
    }
}
