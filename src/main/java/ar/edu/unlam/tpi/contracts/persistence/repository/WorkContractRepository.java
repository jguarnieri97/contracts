package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad WorkContractEntity.
 */
@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {

    /**
     * Busca una lista de contratos por el ID del solicitante.
     *
     * @param applicantId ID del solicitante a buscar.
     * @return la lista de contratos asociados al solicitante, ordenados por fecha de inicio y ID descendente.
     */
    @Query("SELECT w FROM WorkContractEntity w " +
            "WHERE w.applicantEntity.id = :applicantId " +
            "ORDER BY w.dateFrom DESC, w.id DESC")
    List<WorkContractEntity> findByApplicantId(@Param("applicantId") Long applicantId);

    /**
     * Busca una lista de contratos por el ID del proveedor.
     *
     * @param supplierId ID del proveedor a buscar.
     * @return la lista de contratos asociados al proveedor, ordenados por fecha de inicio y ID descendente.
     */
    @Query("SELECT w FROM WorkContractEntity w " +
            "WHERE w.supplierEntity.id = :supplierId " +
            "ORDER BY w.dateFrom DESC, w.id DESC")
    List<WorkContractEntity> findBySupplierId(@Param("supplierId") Long supplierId);

    /**
     * Buscar una lista de la entidad WorkContractRepository por un rango de fecha
     * del campo dateFrom y estatus.
     *
     * @param workerId ID del trabajador a buscar.
     * @param states lista de estados del contrato a filtrar.
     * @param start fecha de inicio del rango.
     * @param end fecha de fin del rango.
     * @return la lista filtrada.
     */
    @Query("SELECT w FROM WorkContractEntity w " +
            "WHERE :workerId MEMBER OF w.workers " +
            "AND w.state IN :states " +
            "AND w.dateFrom BETWEEN :start AND :end " +
            "ORDER BY w.dateFrom DESC")
    List<WorkContractEntity> findByWorkersContainingStatesAndDateRange(
            @Param("workerId") Long workerId,
            @Param("states") List<WorkStateEnum> states,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}