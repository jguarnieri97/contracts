package ar.edu.unlam.tpi.contracts.persistence.dao;

import ar.edu.unlam.tpi.contracts.model.DeliveryNote;

/**
 * Interfaz para la persistencia de remitos.
 */
public interface DeliveryNoteDAO {
    /**
     * Busca una nota de entrega por su ID.
     * @param id
     * @return
     */
    DeliveryNote findDeliveryNoteById(Long id);

    /**
     * Guarda una nota de entrega en la base de datos.
     */
    void saveDeliveryNote(DeliveryNote deliveryNote);
}   
