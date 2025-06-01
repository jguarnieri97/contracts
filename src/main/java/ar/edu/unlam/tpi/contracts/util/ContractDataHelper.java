package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

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
                new WorkContractEntity("1p",120000.0, LocalDate.now(), LocalDate.of(2025, 9, 15),
                        WorkStateEnum.PENDING, "Mantenimiento general", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_3_WORKER_1, SUPPLIER_3_WORKER_2)),

                new WorkContractEntity("2p",180000.0, LocalDate.now(), LocalDate.of(2025, 10, 20),
                        WorkStateEnum.PENDING, "Reparación de cañerías", SUPPLIER_1, APPLICANT_2, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity("3p",220000.0, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 7, 1),
                        WorkStateEnum.PENDING, "Cambio de luminarias", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_1)),


                // INITIATED

                new WorkContractEntity("4p",50000.0, LocalDate.now(), LocalDate.of(2025, 5, 15),
                        WorkStateEnum.INITIATED, "Reparacion de puerta", SUPPLIER_3, APPLICANT_2, List.of(SUPPLIER_3_WORKER_1)),

                new WorkContractEntity("5p",670000.0, LocalDate.now(), LocalDate.of(2025, 5, 15),
                        WorkStateEnum.INITIATED, "Mantenimiento de aires acondicionados", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_1, SUPPLIER_2_WORKER_2)),

                new WorkContractEntity("6p",125000.0, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 10),
                        WorkStateEnum.INITIATED, "Instalación de cámaras de seguridad", SUPPLIER_3, APPLICANT_2, List.of(SUPPLIER_3_WORKER_2)),

                new WorkContractEntity("7p",305000.0, LocalDate.of(2025, 5, 5), LocalDate.of(2025, 7, 5),
                        WorkStateEnum.INITIATED, "Mantenimiento de aires acondicionados", SUPPLIER_1, APPLICANT_1, List.of(SUPPLIER_1_WORKER_1)),

                new WorkContractEntity("8p",120000.0, LocalDate.of(2025, 5, 3), LocalDate.of(2025, 6, 3),
                        WorkStateEnum.INITIATED, "Colocación de pisos vinílicos", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_2_WORKER_2)),

                // FINALIZED
                new WorkContractEntity("9p",85000.0, LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 20),
                        WorkStateEnum.FINALIZED, "Reparación de ascensor", SUPPLIER_1, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity("10p",175000.0, LocalDate.of(2025, 3, 20), LocalDate.of(2025, 4, 15),
                        WorkStateEnum.FINALIZED, "Colocación de carteles corporativos", SUPPLIER_2, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity("11p",60000.0, LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5),
                        WorkStateEnum.FINALIZED, "Limpieza post-obra", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2)),

                new WorkContractEntity("12p",200000.0, LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 10),
                        WorkStateEnum.FINALIZED, "Servicio de limpieza", SUPPLIER_3, APPLICANT_1, List.of(SUPPLIER_1_WORKER_2))

        );
    }
}