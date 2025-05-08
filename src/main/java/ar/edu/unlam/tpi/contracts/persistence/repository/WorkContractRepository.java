package ar.edu.unlam.tpi.contracts.persistence.repository;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkContractRepository extends JpaRepository<WorkContractEntity, Long> {
    
}