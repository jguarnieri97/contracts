package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkState;

import java.time.LocalDate;
import java.util.List;

public class ContractDataHelper {
    private static final Long APPLICANT_1 = 1L;
    private static final Long APPLICANT_2 = 2L;
    private static final Long SUPPLIER_1 = 1L;
    private static final Long SUPPLIER_2 = 2L;
    private static final Long SUPPLIER_3 = 3L;
    private static final Long SUPPLIER_1_WORKER_1 = 1L;
    private static final Long SUPPLIER_1_WORKER_2 = 2L;
    private static final Long SUPPLIER_2_WORKER_1 = 3L;
    private static final Long SUPPLIER_2_WORKER_2 = 4L;
    private static final Long SUPPLIER_3_WORKER_1 = 5L;
    private static final Long SUPPLIER_3_WORKER_2 = 6L;

    public static List<WorkContractEntity> getContracts() {


        return List.of(
                // PENDING
                new WorkContractEntity(120000.0, LocalDate.now(), LocalDate.of(2025, 9, 15),
                        WorkState.PENDING, "Mantenimiento general", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_3_WORKER_1, SUPPLIER_3_WORKER_2)),

                new WorkContractEntity(180000.0, LocalDate.now(), LocalDate.of(2025, 10, 20),
                        WorkState.PENDING, "Reparación de cañerías", SUPPLIER_1, APPLICANT_2, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity(220000.0, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 7, 1),
                        WorkState.PENDING, "Cambio de luminarias", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_1)),


                // INITIATED

                new WorkContractEntity(50000.0, LocalDate.now(), LocalDate.of(2025, 5, 15),
                        WorkState.INITIATED, "Reparacion de puerta", SUPPLIER_3, APPLICANT_2, List.of(SUPPLIER_3_WORKER_1)),

                new WorkContractEntity(670000.0, LocalDate.now(), LocalDate.of(2025, 5, 15),
                        WorkState.INITIATED, "Mantenimiento de aires acondicionados", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_1, SUPPLIER_2_WORKER_2)),

                new WorkContractEntity(125000.0, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 10),
                        WorkState.INITIATED, "Instalación de cámaras de seguridad", SUPPLIER_3, APPLICANT_2, List.of(SUPPLIER_3_WORKER_2)),

                new WorkContractEntity(305000.0, LocalDate.of(2025, 5, 5), LocalDate.of(2025, 7, 5),
                        WorkState.INITIATED, "Mantenimiento de aires acondicionados", SUPPLIER_1, APPLICANT_1, List.of(SUPPLIER_1_WORKER_1)),

                new WorkContractEntity(120000.0, LocalDate.of(2025, 5, 3), LocalDate.of(2025, 6, 3),
                        WorkState.INITIATED, "Colocación de pisos vinílicos", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_2)),

                // FINALIZED
                new WorkContractEntity(85000.0, LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 20),
                        WorkState.FINALIZED, "Reparación de ascensor", SUPPLIER_1, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity(175000.0, LocalDate.of(2025, 3, 20), LocalDate.of(2025, 4, 15),
                        WorkState.FINALIZED, "Colocación de carteles corporativos", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity(60000.0, LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5),
                        WorkState.FINALIZED, "Limpieza post-obra", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity(200000.0, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 10),
                        WorkState.FINALIZED, "Servicio de limpieza", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2))

        );
    }
}