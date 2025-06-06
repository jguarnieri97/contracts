package ar.edu.unlam.tpi.contracts.beans;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.util.ContractDataHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.start.mode", havingValue = "test", matchIfMissing = true)
public class DataInitializer implements CommandLineRunner {

    private final WorkContractRepository workContractRepository;

    @Override
    public void run(String... args) throws Exception {
        List<WorkContractEntity> contracts = ContractDataHelper.getContracts();
        workContractRepository.saveAll(contracts);

        log.info("🟢 Se cargaron {} contratos iniciales", contracts.size());
    }
}