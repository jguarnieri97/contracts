package ar.edu.unlam.tpi.contracts.persistence.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteNotFoundException;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteRepositoryException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.persistence.dao.DeliveryNoteDAO;
import ar.edu.unlam.tpi.contracts.persistence.repository.DeliveryNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryNoteDAOImpl implements DeliveryNoteDAO{
    
    private final DeliveryNoteRepository  deliveryNoteRepository;
    

    @Override
    public DeliveryNote findDeliveryNoteById(Long id){
        log.info("Buscando nota de entrega por ID: {}", id);

        Optional<DeliveryNote> deliveryNote;
        
        try{
            deliveryNote = deliveryNoteRepository.findById(id);
        }catch(Exception e){
            log.error("Error interno al buscar el remito: {}", e.getMessage());
            throw new DeliveryNoteRepositoryException("Error interno al buscar remito: " + id);
        }

        if(deliveryNote.isEmpty()){
            log.error("No se encontró una nota de entrega con el ID: {}", id);
            throw new DeliveryNoteNotFoundException("No se encontró una nota de entrega con el ID: " + id);
        }

        return deliveryNote.get();
    }

    @Override
    public void saveDeliveryNote(DeliveryNote deliveryNote) {
        try {
            log.info("Guardando nota de entrega con ID: {}", deliveryNote.getId());
            deliveryNoteRepository.save(deliveryNote);
            log.info("Nota de entrega guardada exitosamente con ID: {}", deliveryNote.getId());
        } catch (Exception e) {
            log.error("Error interno al guardar la nota de entrega: {}", e.getMessage());
            throw new DeliveryNoteRepositoryException("Error interno al guardar la nota de entrega: " + e.getMessage());
        }
    }

}
