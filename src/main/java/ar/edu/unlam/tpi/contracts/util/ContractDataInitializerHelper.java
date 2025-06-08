package ar.edu.unlam.tpi.contracts.util;

import ar.edu.unlam.tpi.contracts.model.ApplicantEntity;
import ar.edu.unlam.tpi.contracts.model.SupplierEntity;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;

import java.time.LocalDate;
import java.util.List;

public class ContractDataInitializerHelper {
    private static final Long APPLICANT_1 = 1L;
    private static final Long APPLICANT_2 = 2L;
    private static final Long SUPPLIER_1 = 1L;
    private static final Long SUPPLIER_2 = 2L;
    private static final Long SUPPLIER_1_WORKER_1 = 1L;
    private static final Long SUPPLIER_1_WORKER_2 = 2L;
    private static final Long SUPPLIER_2_WORKER_1 = 3L;
    private static final Long SUPPLIER_2_WORKER_2 = 4L;

    public static List<WorkContractEntity> getContracts() {

        // Primero applicants
        ApplicantEntity applicant1 = ApplicantEntity.builder().id(1L).name("Solicitante 1").build();
        ApplicantEntity applicant2 = ApplicantEntity.builder().id(2L).name("Solicitante 1").build();

        // Suppliers
        SupplierEntity supplier1 = SupplierEntity.builder().id(1L).name("Proveedor 1").build();
        SupplierEntity supplier2 = SupplierEntity.builder().id(2L).name("Proveedor 2").build();

        return List.of(
                // PENDING
                WorkContractEntity.builder()
                        .codeNumber("1p")
                        .price(120000.0)
                        .dateFrom(LocalDate.now())
                        .dateTo(LocalDate.of(2025, 9, 15))
                        .state(WorkStateEnum.PENDING)
                        .detail("Mantenimiento general")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_2, SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("2p")
                        .price(180000.0)
                        .dateFrom(LocalDate.now())
                        .dateTo(LocalDate.of(2025, 10, 20))
                        .state(WorkStateEnum.PENDING)
                        .detail("Reparación de cañerías")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant2)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("3p")
                        .price(220000.0)
                        .dateFrom(LocalDate.of(2025, 5, 1))
                        .dateTo(LocalDate.of(2025, 7, 1))
                        .state(WorkStateEnum.PENDING)
                        .detail("Cambio de luminarias")
                        .supplierEntity(supplier2)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_2_WORKER_1))
                        .build(),

                // INITIATED

                WorkContractEntity.builder()
                        .codeNumber("4p")
                        .price(50000.0)
                        .dateFrom(LocalDate.now())
                        .dateTo(LocalDate.of(2025, 5, 15))
                        .state(WorkStateEnum.INITIATED)
                        .detail("Reparacion de puerta")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant2)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("5p")
                        .price(670000.0)
                        .dateFrom(LocalDate.now())
                        .dateTo(LocalDate.of(2025, 5, 15))
                        .state(WorkStateEnum.INITIATED)
                        .detail("Mantenimiento de aires acondicionados")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_2_WORKER_1, SUPPLIER_2_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("6p")
                        .price(125000.0)
                        .dateFrom(LocalDate.of(2025, 5, 10))
                        .dateTo(LocalDate.of(2025, 6, 10))
                        .state(WorkStateEnum.INITIATED)
                        .detail("Instalación de cámaras de seguridad")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_1))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("7p")
                        .price(305000.0)
                        .dateFrom(LocalDate.of(2025, 5, 5))
                        .dateTo(LocalDate.of(2025, 7, 5))
                        .state(WorkStateEnum.INITIATED)
                        .detail("Mantenimiento de aires acondicionados")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_1))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("8p")
                        .price(120000.0)
                        .dateFrom(LocalDate.of(2025, 5, 3))
                        .dateTo(LocalDate.of(2025, 6, 3))
                        .state(WorkStateEnum.INITIATED)
                        .detail("Colocación de pisos vinílicos")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_2_WORKER_2))
                        .build(),

                // FINALIZED
                WorkContractEntity.builder()
                        .codeNumber("9p")
                        .price(85000.0)
                        .dateFrom(LocalDate.of(2025, 4, 10))
                        .dateTo(LocalDate.of(2025, 4, 20))
                        .state(WorkStateEnum.FINALIZED)
                        .detail("Reparación de ascensor")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("10p")
                        .price(175000.0)
                        .dateFrom(LocalDate.of(2025, 3, 20))
                        .dateTo(LocalDate.of(2025, 4, 15))
                        .state(WorkStateEnum.FINALIZED)
                        .detail("Colocación de carteles corporativos")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("11p")
                        .price(60000.0)
                        .dateFrom(LocalDate.of(2025, 4, 1))
                        .dateTo(LocalDate.of(2025, 4, 5))
                        .state(WorkStateEnum.FINALIZED)
                        .detail("Limpieza post-obra")
                        .supplierEntity(supplier1)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build(),

                WorkContractEntity.builder()
                        .codeNumber("12p")
                        .price(200000.0)
                        .dateFrom(LocalDate.of(2025, 5, 8))
                        .dateTo(LocalDate.of(2025, 5, 10))
                        .state(WorkStateEnum.FINALIZED)
                        .detail("Servicio de limpieza")
                        .supplierEntity(supplier2)
                        .applicantEntity(applicant1)
                        .workers(List.of(SUPPLIER_1_WORKER_2))
                        .build()
        );
    }
}