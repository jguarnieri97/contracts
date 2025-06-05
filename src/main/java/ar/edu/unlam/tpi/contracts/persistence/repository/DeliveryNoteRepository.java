package ar.edu.unlam.tpi.contracts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.contracts.model.DeliveryNote;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote,Long>{
    
}
