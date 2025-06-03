package ar.edu.unlam.tpi.contracts.persistence.dao;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;

public interface WorkContractDAO extends GenericInterfaceDAO<WorkContractEntity, Long> {

    List<WorkContractEntity> findByApplicantId(Long applicantId);

    List<WorkContractEntity> findBySupplierId(Long supplierId);

    List<WorkContractEntity> findByWorkersContainingStatesAndDateRange(Long workerId, List<WorkStateEnum> states, LocalDate start, LocalDate end);

}
