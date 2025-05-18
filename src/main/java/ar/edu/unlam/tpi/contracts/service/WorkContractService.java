package ar.edu.unlam.tpi.contracts.service;


import ar.edu.unlam.tpi.contracts.dto.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.dto.WorkContractUpdateRequest;

public interface WorkContractService {

    /**
     * Crea un nuevo contrato
     * @param request
     *      El request del contrato
     * @return
     *      El contrato creado
     */
    WorkContractResponse createContract(WorkContractRequest request);

    /**
     * Actualiza el estado de un contrato
     * @param id
     *      El id del contrato
     * @param request
     *      El request del contrato
     */
    void updateContractState(Long id, WorkContractUpdateRequest request);

    /**
     * Obtiene un contrato por su id
     * @param id
     *      El id del contrato
     * @return
     *      El contrato encontrado
     */
    WorkContractResponse getContractById(Long id);
}