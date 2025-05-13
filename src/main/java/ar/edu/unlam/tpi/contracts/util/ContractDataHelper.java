package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;

public class ContractDataHelper {

    public static List<WorkContractEntity> getContracts() {
        return List.of(
                 new WorkContractEntity(180000.0, LocalDate.of(2025, 8, 31), LocalDate.of(2025, 10, 20),
                    WorkState.PENDING, "Reparación de cañerías", 2L, 1L, List.of(5L)),
                
                    new WorkContractEntity(150000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Instalación eléctrica", 1L, 2L, List.of(3L, 4L)),


                new WorkContractEntity(200000.0, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 10),
                        WorkState.FINALIZED, "Servicio de limpieza", 3L, 2L, List.of(6L, 7L))
        );
    }
}