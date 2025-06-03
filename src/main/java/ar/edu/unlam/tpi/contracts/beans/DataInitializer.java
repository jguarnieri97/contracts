package ar.edu.unlam.tpi.contracts.beans;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import ar.edu.unlam.tpi.contracts.util.ContractDataHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final WorkContractRepository workContractRepository;

    @Override
    public void run(String... args) throws Exception {
        List<WorkContractEntity> contracts = ContractDataHelper.getContracts();
        workContractRepository.saveAll(contracts);

        log.info("🟢 Se cargaron {} contratos iniciales", contracts.size());
    }
}