package ar.edu.unlam.tpi.contracts.persistence.dao.impl;

import ar.edu.unlam.tpi.contracts.exception.ContractNotFoundException;
import ar.edu.unlam.tpi.contracts.exception.WorkContractRepositoryException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.persistence.repository.WorkContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkContractDAOImpl implements WorkContractDAO {

    private final WorkContractRepository workContractRepository;

    @Override
    public List<WorkContractEntity> findAll() {
        try {
            log.info("Buscando todos los contratos de trabajo");
            return workContractRepository.findAll();
        } catch (Exception e) {
            log.error("Error interno al buscar todos los contratos: {}", e.getMessage());
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public WorkContractEntity findById(Long id) {
        try {
            log.info("Buscando contrato por ID: {}", id);
            return workContractRepository.findById(id)
                    .orElseThrow(() -> new ContractNotFoundException("WorkContract not found"));
        }
        catch(ContractNotFoundException e){
            log.error("Contrato no encontrado: {}", e.getDetail());
            throw new ContractNotFoundException(e.getDetail());
        }
        catch (Exception e) {
            log.error("Error interno al buscar contrato: {}", e.getMessage());
            throw new WorkContractRepositoryException(e.getMessage());
        }
    }

    @Override
    public WorkContractEntity save(WorkContractEntity entity) {
        try {
            log.info("Guardando contrato con ID: {}", entity.getId());
            return workContractRepository.save(entity);
        } catch (Exception e) {
            log.error("Error interno al guardar contrato: {}", e.getMessage());
            throw new WorkContractRepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Eliminando contrato con ID: {}", id);
            workContractRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error interno al eliminar contrato: {}", e.getMessage());
            throw new InternalException(e.getMessage());
        }
    }


    @Override
    public List<WorkContractEntity> findByApplicantId(Long applicantId) {
        try {
            log.info("Buscando contratos por ID de solicitante: {}", applicantId);
            return workContractRepository.findByApplicantId(applicantId);
        } catch (Exception e) {
            log.error("Error al buscar contratos por solicitante ID: {}", applicantId);
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<WorkContractEntity> findBySupplierId(Long supplierId) {
        try {
            log.info("Buscando contratos por ID de proveedor: {}", supplierId);
            return workContractRepository.findBySupplierId(supplierId);
        } catch (Exception e) {
            log.error("Error al buscar contratos por proveedor ID: {}", supplierId);
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<WorkContractEntity> findByWorkersContainingStatesAndDateRange(Long workerId, List<WorkStateEnum> states, LocalDate start, LocalDate end) {
        try {
            log.info("Buscando contratos por ID de trabajador: {} con estados: {} y rango de fechas: {} a {}", workerId, states, start, end);   
            return workContractRepository.findByWorkersContainingStatesAndDateRange(workerId, states, start, end);
        } catch (Exception e) {
            log.error("Error al buscar contratos por trabajador ID: {} con estados: {} y rango de fechas: {} a {}", workerId, states, start, end);
            throw new InternalException(e.getMessage());
        }
    }
}
