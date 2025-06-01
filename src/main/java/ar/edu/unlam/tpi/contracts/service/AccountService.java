package ar.edu.unlam.tpi.contracts.service;
import java.util.List;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;

public interface AccountService {

    /**
     * Obtiene los contratos de un solicitante
     * @param applicantId
     *      El id del solicitante
     * @param limit
     *      Si es true, limita a 4 resultados, si es false no hay límite
     * @return
     *      Una lista de contratos
     */
    List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Boolean limit);

    /**
     * Obtiene los contratos de un proveedor
     * @param supplierId
     *      El id del proveedor
     * @param limit
     *      Si es true, limita a 4 resultados, si es false no hay límite
     * @return
     *      Una lista de contratos
     */
    List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Boolean limit);

    
    /**
     * Obtiene los contratos de un trabajador
     * @param workerId
     *      El id del trabajador
     * @return
     *      Una lista de contratos
     */
    List<WorkContractResponse> getContractsByWorkerId(Long workerId);

}
