package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {

    @Query("SELECT w FROM WorkContractEntity w WHERE w.applicantId = :applicantId ORDER BY w.date DESC")
    List<WorkContractEntity> findByApplicantId(@Param("applicantId") Long applicantId);
    
    List<WorkContractEntity> findBySupplierIdAndStateIn(Long supplierId, List<WorkState> states);
    
}