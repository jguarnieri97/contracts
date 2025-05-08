package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {

    List<WorkContractEntity> findByApplicantId(Long applicantId);
    List<WorkContractEntity> findBySupplierIdAndStateIn(Long supplierId, List<WorkState> states);
    
}