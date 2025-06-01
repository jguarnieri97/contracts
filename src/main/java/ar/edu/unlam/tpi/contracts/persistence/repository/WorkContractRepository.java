package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {

    @Query("SELECT w FROM WorkContractEntity w WHERE w.applicantId = :applicantId ORDER BY w.dateFrom DESC, w.id DESC")
    List<WorkContractEntity> findByApplicantId(@Param("applicantId") Long applicantId);

    @Query("SELECT w FROM WorkContractEntity w WHERE w.supplierId = :supplierId ORDER BY w.dateFrom DESC, w.id DESC")
    List<WorkContractEntity> findBySupplierIdAndStates(@Param("supplierId") Long supplierId);

    @Query("""
    SELECT w FROM WorkContractEntity w
    WHERE :workerId MEMBER OF w.workers
    AND w.state IN :states
    AND w.dateFrom BETWEEN :start AND :end
    ORDER BY w.dateFrom DESC
""")
List<WorkContractEntity> findByWorkersContainingAndDateRange(
    @Param("workerId") Long workerId,
    @Param("states") List<WorkStateEnum> states,
    @Param("start") LocalDate start,
    @Param("end") LocalDate end
);
}