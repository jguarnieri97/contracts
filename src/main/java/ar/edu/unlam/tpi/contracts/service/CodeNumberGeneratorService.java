package ar.edu.unlam.tpi.contracts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeNumberGeneratorService {

    private final JdbcTemplate jdbcTemplate;

    public String generateCodeNumber() {
        String sql = "SELECT nextval('work_contract_sequence')";
        Long nextValue = jdbcTemplate.queryForObject(sql, Long.class);
        if (nextValue == null) {
            throw new RuntimeException("No se pudo obtener el siguiente valor de la secuencia");
        }
        return String.valueOf(nextValue);
    }
}
