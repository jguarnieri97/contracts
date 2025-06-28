package ar.edu.unlam.tpi.contracts.persistence.dao;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz para la persistencia de contratos de trabajo.
 */
public interface WorkContractDAO extends GenericInterfaceDAO<WorkContractEntity, Long> {
    /**
     * Busca los contratos de trabajo asociados a un solicitante por su ID.
     * @param applicantId
     * @return 
     */
    List<WorkContractEntity> findByApplicantId(Long applicantId);

    /**
     * Busca los contratos de trabajo asociados a un proveedor por su ID.
     * @param supplierId
     * @return 
     */
    List<WorkContractEntity> findBySupplierId(Long supplierId);

    /**
     * Busca los contratos de trabajo asociados a un trabajador por su ID.
     * @param workerId
     * @param states
     * @param start
     * @param end
     * @return
     */
    List<WorkContractEntity> findByWorkersContainingStatesAndDateRange(Long workerId, List<WorkStateEnum> states, LocalDate start, LocalDate end);

}
