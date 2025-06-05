package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteNotFoundException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.DeliveryNoteDAO;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.service.FileCreatorService;
import ar.edu.unlam.tpi.contracts.service.task.DeliveryNoteExecutorTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final WorkContractDAO workContractRepository;
    private final BlockchainServiceClient blockchainClient;
    private final ExecutorService executorService;
    private final FileCreatorService fileCreatorService;
    private final DeliveryNoteDAO deliveryNoteDAO;


    @Override
    public void createDeliveryNote(DeliveryNoteRequest request) {
        log.info("Creando delivery-note, request recibido: {}", request);

        WorkContractEntity contract = workContractRepository.findById(request.getContractId());

        byte[] file = fileCreatorService.createFile(request,contract);
        DeliveryNote deliveryNote = new DeliveryNote(contract, file);

        contract.setDeliveryNote(deliveryNote);
        workContractRepository.save(contract);
        
        // Ejecutar la tarea asíncrona para certificar el documento
        executorService.execute(new DeliveryNoteExecutorTask(workContractRepository, blockchainClient, contract));
        log.info("Tarea asíncrona de certificación iniciada para el contrato: {}", contract.getId());
    }

    @Override
    public DeliveryNoteResponse getDeliveryNote(Long contractId) {
        log.info("Obteniendo remito - contractId: {}", contractId);
        WorkContractEntity contract = workContractRepository.findById(contractId);
        DeliveryNote deliveryNote = contract.getDeliveryNote();

        if (Objects.isNull(deliveryNote)) {
            log.error("No existe un remito asociado al contrato con ID: {}", contractId);
            throw new DeliveryNoteNotFoundException("No existe un remito asociado al contrato con ID: " + contractId);
        }

        BlockchainVerifyRequest request = BlockchainVerifyRequest.builder()
                .txHash(deliveryNote.getTxHash())
                .data(deliveryNote.getData())
                .build();

        log.info("Realizando verificación del certificado");
        blockchainClient.verifyCertificate(request);
        log.info("Certificado verificado con éxito!");

        return DeliveryNoteResponse.builder()
                .id(deliveryNote.getId())
                .data(deliveryNote.getData())
                .txHash(deliveryNote.getTxHash())
                .dataHash(deliveryNote.getDataHash())
                .blockNumber(deliveryNote.getBlockNumber())
                .createdAt(deliveryNote.getCreatedAt().toString())
                .build();
    }

    @Override
    public void signatureDeliveryNote(Long id, DeliverySignatureRequest request) {
        log.info("Firmando remito con ID: {}", id);
        DeliveryNote deliveryNote = deliveryNoteDAO.findDeliveryNoteById(id);
        byte[] dataUpdated = fileCreatorService.signFile(deliveryNote, request.getSignature());
        
        deliveryNote.setData(dataUpdated);
        executorService.execute(new DeliveryNoteExecutorTask(workContractRepository, blockchainClient, deliveryNote.getWorkContract()));
        deliveryNoteDAO.saveDeliveryNote(deliveryNote);
        log.info("Remito firmado exitosamente");
    }
}
