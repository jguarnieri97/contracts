package ar.edu.unlam.tpi.contracts.controller.integration;

import ar.edu.unlam.tpi.contracts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.contracts.dto.response.WorkContractResponse;
import ar.edu.unlam.tpi.contracts.util.Constants;
import ar.edu.unlam.tpi.contracts.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenApplicantId_whenGetContractsByApplicantId_thenReturnsContracts() throws Exception {
        // Given
        Long applicantId = TestUtils.APPLICANT_ID;
        Boolean limit = true;

        // When
        MvcResult result = mockMvc.perform(get("/contracts/v1/accounts/applicant/{id}", applicantId)
                .param("limit", limit.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        GenericResponse<List<WorkContractResponse>> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructParametricType(
                        GenericResponse.class,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, WorkContractResponse.class)
                )
        );

        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
        assertEquals(4, response.getData().size());
    }

    @Test
    void givenSupplierId_whenGetContractsBySupplierId_thenReturnsContracts() throws Exception {
        // Given
        Long supplierId = TestUtils.SUPPLIER_ID;
        Boolean limit = true;

        // When
        MvcResult result = mockMvc.perform(get("/contracts/v1/accounts/supplier/{id}", supplierId)
                .param("limit", limit.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        GenericResponse<List<WorkContractResponse>> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructParametricType(
                        GenericResponse.class,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, WorkContractResponse.class)
                )
        );

        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
        assertEquals(4, response.getData().size());
    }


    @Test
    void givenNonExistentApplicantId_whenGetContractsByApplicantId_thenReturnException() throws Exception {
        // Given
        Long nonExistentApplicantId = 999L;
        Boolean limit = true;

        // When
        mockMvc.perform(get("/contracts/v1/accounts/applicant/{id}", nonExistentApplicantId)
                .param("limit", limit.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
} 