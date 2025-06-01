package ar.edu.unlam.tpi.contracts.persistence.dao.impl;

import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.exception.WorkContractRepositoryException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkContractDAOImpl implements WorkContractDAO {

    private final WorkContractRepository workContractRepository;

    @Override
    public WorkContractEntity findWorkContractById(Long id) {
        log.info("Buscando contrato por ID: {}", id);
        Optional<WorkContractEntity> contract;

        try {
            contract = workContractRepository.findById(id);
        } catch (Exception e) {
            log.error("Error interno al buscar contrato: {}", e.getMessage());
            throw new WorkContractRepositoryException(e.getMessage());
        }

        if (contract.isEmpty()) {
            log.error("No se encontro contrato por el ID: {}", id);
            throw new ContractNotFoundException("No se encontró el contrato con ID: " + id);
        }

        log.info("Se encontró un contrato con ID: {}", id);
        return contract.get();
    }

    @Override
    public void saveWorkContract(WorkContractEntity workContract) {
        try {
            log.info("Guardando contrato con ID: {}", workContract.getId());
            workContractRepository.save(workContract);
            log.info("Se guardó contrato exitosamente con el ID: {}", workContract.getId());
        } catch (Exception e) {
            log.error("Error interno al guardar contrato: {}", e.getMessage());
            throw new WorkContractRepositoryException(e.getMessage());
        }
    }
}
