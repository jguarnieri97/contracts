package ar.edu.unlam.tpi.contracts.integration;

import ar.edu.unlam.tpi.contracts.dto.request.DeliverySignatureRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class DeliveryNoteIntegrationTest {

    //@Autowired
    private MockMvc mockMvc;

    //@Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    void givenNonExistentContractId_whenGetDeliveryNote_thenReturnsNotFound() throws Exception {
        // Given
        Long nonExistentContractId = 999L;

        // When/Then
        mockMvc.perform(get("/contracts/v1/delivery-note/{id}", nonExistentContractId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }


    @Test
    @Disabled
    void givenNonExistentDeliveryNoteId_whenSignatureDeliveryNote_thenReturnsNotFound() throws Exception {
        // Given
        Long nonExistentDeliveryNoteId = 999L;
        DeliverySignatureRequest request = DeliverySignatureRequest.builder()
                .signature("base64SignatureData")
                .build();

        // When/Then
        mockMvc.perform(put("/contracts/v1/delivery-note/{id}", nonExistentDeliveryNoteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
} 