package ar.edu.unlam.tpi.contracts.service;
import java.util.List;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;

public interface AccountService {

    /**
     * Obtiene los contratos de un solicitante
     * @param applicantId
     *      El id del solicitante
     * @param limit
     *      El limite de contratos a obtener
     * @return
     *      Una lista de contratos
     */

    List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Integer limit);

    /**
     * Obtiene los contratos de un proveedor
     * @param supplierId
     *      El id del proveedor
     * @param limit
     *      El limite de contratos a obtener
     * @return
     *      Una lista de contratos
     */
    List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Integer limit);

    
    /**
     * Obtiene los contratos de un trabajador
     * @param workerId
     *      El id del trabajador
     * @return
     *      Una lista de contratos
     */
    List<WorkContractResponse> getContractsByWorkerId(Long workerId);

}
