package ar.edu.unlam.tpi.contracts.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@ConditionalOnProperty(name = "app.start.mode", havingValue = "test", matchIfMissing = true)
public class SequenceInitializer {

    private final DataSource dataSource;

    public SequenceInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initializeSequence() {
        String sql = """
            CREATE SEQUENCE work_contract_sequence
            START WITH 1
            INCREMENT BY 1
            NO CYCLE;
        """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Secuencia 'work_contract_sequence' creada en memoria.");
        } catch (Exception e) {
            System.err.println("Error al crear la secuencia: " + e.getMessage());
        }
    }
}