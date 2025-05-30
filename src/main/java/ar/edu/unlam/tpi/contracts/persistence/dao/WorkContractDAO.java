package ar.edu.unlam.tpi.contracts.persistence.dao;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;

public interface WorkContractDAO {

    WorkContractEntity findWorkContractById(Long id);

    void saveWorkContract(WorkContractEntity workContract);

}
