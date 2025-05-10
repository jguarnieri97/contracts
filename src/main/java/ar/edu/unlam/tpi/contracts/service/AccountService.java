package ar.edu.unlam.tpi.contracts.service;
import java.util.List;
import ar.edu.unlam.tpi.contracts.dto.WorkContractResponse;

public interface AccountService {

    List<WorkContractResponse> getContractsByApplicantId(Long applicantId, Integer limit);

    List<WorkContractResponse> getContractsBySupplierId(Long supplierId, Integer limit);

    List<WorkContractResponse> getContractsByWorkerId(Long workerId);

}
