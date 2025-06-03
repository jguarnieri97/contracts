package ar.edu.unlam.tpi.contracts.service.task;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.request.BlockchainRequest;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.util.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeliveryNoteExecutorTask implements Runnable {

    private final WorkContractDAO repository;
    private final BlockchainServiceClient client;
    private final WorkContractEntity contract;

    public DeliveryNoteExecutorTask(WorkContractDAO repository, BlockchainServiceClient client, WorkContractEntity contract) {
        this.repository = repository;
        this.client = client;
        this.contract = contract;
    }

    @Override
    public void run() {
        var contractId = contract.getId();
        log.info("::: Contrato: {} - Comienza el proceso de certificación por Blockchain :::", contractId);
        var request = this.buildBlockchainRequest(contract.getDeliveryNote().getData());

        log.info("Contrato: {} - Enviando request de certificación a blockchain", contractId);
        var response = client.certificateFile(request);
        log.info("Contrato: {} - Respuesta de blockchain recibida: {}", contractId, Converter.convertToString(response));

        contract.getDeliveryNote().setTxHash(response.getTxHash());
        contract.getDeliveryNote().setDataHash(response.getDataHash());
        contract.getDeliveryNote().setBlockNumber(response.getBlockNumber());

        repository.save(contract);
        log.info("::: Contrato: {} - FIN del proceso de certificación por Blockchain :::", contractId);
    }


    private BlockchainRequest buildBlockchainRequest(byte[] file) {
        return BlockchainRequest.builder()
                .data(file)
                .build();
    }
}
