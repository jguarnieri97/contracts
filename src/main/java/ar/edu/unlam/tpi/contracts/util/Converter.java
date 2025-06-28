package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.dto.request.RegisterRequest;
import ar.edu.unlam.tpi.contracts.exception.ConverterException;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@UtilityClass
public class Converter {

    public String convertToString(Object object) {
        try {
            log.info("Convirtiendo el objeto a String: {}", object);
            var mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error al convertir el objeto a String: {}", e.getMessage());
            throw new ConverterException(e.getMessage());
        }
    }


    public RegisterRequest toRegisterRequest(WorkContractEntity contract, List<String> images) {
        return RegisterRequest.builder()
                .applicantId(contract.getApplicantEntity().getId())
                .supplierId(contract.getSupplierEntity().getId())
                .images(images)
                .build();
    }
}
