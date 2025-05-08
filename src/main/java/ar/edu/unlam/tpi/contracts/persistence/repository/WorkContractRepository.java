package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {

    @Query("SELECT w FROM WorkContractEntity w WHERE w.applicantId = :applicantId AND w.dateFrom = :today ORDER BY w.dateFrom DESC")
List<WorkContractEntity> findByApplicantIdAndToday(@Param("applicantId") Long applicantId, @Param("today") LocalDate today);

    
    @Query("SELECT w FROM WorkContractEntity w WHERE w.supplierId = :supplierId AND w.state IN :states AND w.dateFrom = :today ORDER BY w.dateFrom DESC")
    List<WorkContractEntity> findBySupplierIdAndStateIn(@Param("supplierId")Long supplierId, @Param("states")List<WorkState> states, @Param("today")LocalDate today);
    
}