package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;

public class ContractDataHelper {

    public static List<WorkContractEntity> getContracts() {
        return List.of(
                // PENDING
                new WorkContractEntity(120000.0, LocalDate.now(), LocalDate.of(2025, 9, 15),
                        WorkState.PENDING, "Mantenimiento general", 3L, 1L, List.of(8L, 9L)),

                new WorkContractEntity(180000.0, LocalDate.now(), LocalDate.of(2025, 10, 20),
                        WorkState.PENDING, "Reparación de cañerías", 2L, 1L, List.of(5L)),

                new WorkContractEntity(220000.0, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 7, 1),
                        WorkState.PENDING, "Cambio de luminarias", 4L, 3L, List.of(10L)),

                new WorkContractEntity(95000.0, LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15),
                        WorkState.PENDING, "Pintura interior de oficinas", 5L, 3L, List.of(11L, 12L)),

                // INITIATED
                new WorkContractEntity(150000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Instalación aire acondicionado", 1L, 2L, List.of(3L, 4L)),
                new WorkContractEntity(50000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Reparacion de puerta", 1L, 2L, List.of(3L, 4L)),

                new WorkContractEntity(30000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Limpieza de vidrios", 1L, 2L, List.of(3L, 4L)),

                new WorkContractEntity(40000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Instalación internet", 1L, 2L, List.of(3L, 4L)),

                new WorkContractEntity(670000.0, LocalDate.now(), LocalDate.of(2025, 10, 15),
                        WorkState.INITIATED, "Mantenimiento de aires acondicionados", 1L, 2L, List.of(3L, 4L)),

                new WorkContractEntity(125000.0, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 10),
                        WorkState.INITIATED, "Instalación de cámaras de seguridad", 6L, 3L, List.of(13L)),

                new WorkContractEntity(305000.0, LocalDate.of(2025, 5, 5), LocalDate.of(2025, 7, 5),
                        WorkState.INITIATED, "Refacción de baños", 4L, 2L, List.of(14L, 15L)),

                new WorkContractEntity(120000.0, LocalDate.of(2025, 5, 3), LocalDate.of(2025, 6, 3),
                        WorkState.INITIATED, "Colocación de pisos vinílicos", 2L, 3L, List.of(9L)),

                // FINALIZED
                new WorkContractEntity(85000.0, LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 20),
                        WorkState.FINALIZED, "Servicio de jardinería", 5L, 2L, List.of(16L)),

                new WorkContractEntity(175000.0, LocalDate.of(2025, 3, 20), LocalDate.of(2025, 4, 15),
                        WorkState.FINALIZED, "Colocación de carteles corporativos", 6L, 1L, List.of(17L, 18L)),

                new WorkContractEntity(60000.0, LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5),
                        WorkState.FINALIZED, "Limpieza post-obra", 4L, 1L, List.of(19L)),
                new WorkContractEntity(200000.0, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 10),
                        WorkState.FINALIZED, "Servicio de limpieza", 3L, 2L, List.of(6L, 7L))

        );
    }
}