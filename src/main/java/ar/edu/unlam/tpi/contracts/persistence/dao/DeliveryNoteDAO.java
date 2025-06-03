package ar.edu.unlam.tpi.contracts.persistence.dao;

import ar.edu.unlam.tpi.contracts.model.DeliveryNote;

public interface DeliveryNoteDAO {
    DeliveryNote findDeliveryNoteById(Long id);
}   
