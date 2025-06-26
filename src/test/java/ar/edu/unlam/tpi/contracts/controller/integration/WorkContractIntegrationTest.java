package ar.edu.unlam.tpi.contracts.controller.integration;

import ar.edu.unlam.tpi.contracts.dto.request.WorkContractRequest;
import ar.edu.unlam.tpi.contracts.dto.request.WorkContractUpdateRequest;
import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.model.WorkStateEnum;
import ar.edu.unlam.tpi.contracts.persistence.dao.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.TestUtils;
import ar.edu.unlam.tpi.contracts.util.WorkContractDataHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WorkContractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WorkContractDAO workContractDAO;

    @Test
    void givenValidRequest_whenCreateContract_thenReturnsCreatedResponse() throws Exception {
        // Given
        WorkContractRequest request = WorkContractRequest.builder()
                .price(2000.0)
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(60))
                .detail("New test contract")
                .supplierId(TestUtils.SUPPLIER_ID)
                .applicantId(TestUtils.APPLICANT_ID)
                .workers(List.of(3L, 4L, 5L))
                .build();

        // When
        MvcResult result = mockMvc.perform(post("/contracts/v1/work-contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        GenericResponse<WorkContractResponse> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructParametricType(
                        GenericResponse.class,
                        WorkContractResponse.class
                )
        );

        assertNotNull(response);
        assertEquals(Constants.STATUS_CREATED, response.getCode());
        assertEquals(Constants.CREATED_MESSAGE, response.getMessage());
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
        assertEquals(request.getPrice(), response.getData().getPrice());
        assertEquals(request.getDateFrom(), response.getData().getDateFrom());
        assertEquals(request.getDateTo(), response.getData().getDateTo());
        assertEquals(request.getDetail(), response.getData().getDetail());
        assertEquals(WorkStateEnum.PENDING.name(), response.getData().getState());
        assertEquals(request.getSupplierId(), response.getData().getSupplierId());
        assertEquals(request.getApplicantId(), response.getData().getApplicantId());
        assertEquals(request.getWorkers(), response.getData().getWorkers());
    }

    @Test
    void givenValidRequest_whenUpdateContractState_thenReturnsOkResponse() throws Exception {
        // Given
        WorkContractEntity contract = WorkContractDataHelper.createWorkContractEntity();
        WorkContractEntity savedContract = workContractDAO.save(contract);
        Long contractId = savedContract.getId();
        
        WorkContractUpdateRequest request = WorkContractUpdateRequest.builder()
                .state(WorkStateEnum.INITIATED.name())
                .detail("Updated contract detail")
                .build();

        // When
        MvcResult result = mockMvc.perform(put("/contracts/v1/work-contract/{id}", contractId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        GenericResponse<Void> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                GenericResponse.class
        );

        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.UPDATED_MESSAGE, response.getMessage());
        assertNull(response.getData());

        // Verify the contract was updated
        WorkContractEntity updatedContract = workContractDAO.findById(contractId);
        assertEquals(WorkStateEnum.INITIATED, updatedContract.getState());
        assertEquals(request.getDetail(), updatedContract.getDetail());
    }

    @Test
    void givenValidContractId_whenGetContract_thenReturnsContract() throws Exception {
        // Given
        WorkContractEntity contract = WorkContractDataHelper.createWorkContractEntity();
        WorkContractEntity savedContract = workContractDAO.save(contract);
        Long contractId = savedContract.getId();
        
        // When
        MvcResult result = mockMvc.perform(get("/contracts/v1/work-contract/{id}", contractId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        GenericResponse<WorkContractResponse> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructParametricType(
                        GenericResponse.class,
                        WorkContractResponse.class
                )
        );

        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertNotNull(response.getData());
        assertEquals(contractId, response.getData().getId());
        assertEquals(WorkStateEnum.PENDING.name(), response.getData().getState());
        assertEquals(TestUtils.SUPPLIER_ID, response.getData().getSupplierId());
        assertEquals(TestUtils.APPLICANT_ID, response.getData().getApplicantId());
        assertEquals(List.of(3L, 4L, 5L), response.getData().getWorkers());
    }

    @Test
    void givenNonExistentContractId_whenGetContract_thenReturnsNotFound() throws Exception {
        // Given
        Long nonExistentContractId = 999L;

        // When/Then
        mockMvc.perform(get("/contracts/v1/work-contract/{id}", nonExistentContractId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenInvalidRequest_whenCreateContract_thenReturnsBadRequest() throws Exception {
        // Given
        WorkContractRequest request = WorkContractRequest.builder()
                .price(null) // Required field
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(60))
                .detail("New test contract")
                .supplierId(TestUtils.SUPPLIER_ID)
                .applicantId(TestUtils.APPLICANT_ID)
                .workers(List.of(3L, 4L, 5L))
                .build();

        // When/Then
        mockMvc.perform(post("/contracts/v1/work-contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
} 