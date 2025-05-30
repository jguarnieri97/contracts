package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainVerifyRequest;
import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.dto.response.DeliveryNoteResponse;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteNotFoundException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.service.file.FileCreator;
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

    private final WorkContractDAO repository;
    private final BlockchainServiceClient blockchainClient;
    private final ExecutorService executorService;
    private final FileCreator fileCreator;

    @Override
    public void createDeliveryNote(DeliveryNoteRequest request) {
        log.info("Creando delivery-note, request recibido: {}", request);

        WorkContractEntity contract = repository.findWorkContractById(request.getContractId());

        byte[] file = fileCreator.createFile(request);
        DeliveryNote deliveryNote = new DeliveryNote(contract, file);

        contract.setDeliveryNote(deliveryNote);
        repository.saveWorkContract(contract);

        executorService.execute(new DeliveryNoteExecutorTask(repository, blockchainClient, contract));
    }

    @Override
    public DeliveryNoteResponse getDeliveryNote(Long contractId) {
        log.info("Obteniendo remito - contractId: {}", contractId);
        var contract = repository.findWorkContractById(contractId);
        var deliveryNote = contract.getDeliveryNote();

        if (Objects.isNull(deliveryNote)) {
            log.error("No existe un remito asociado al contrato con ID: {}", contractId);
            throw new DeliveryNoteNotFoundException("No existe un remito asociado al contrato con ID: " + contractId);
        }

        var request = BlockchainVerifyRequest.builder()
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

}
