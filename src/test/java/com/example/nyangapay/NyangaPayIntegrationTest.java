package com.example.nyangapay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class NyangaPayIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFullBusinessFlow() throws Exception {
        // 1. Test Auth Strategy
        mockMvc.perform(post("/api/auth/login")
                .param("method", "PASSWORD")
                .param("user", "admin")
                .param("credentials", "root"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Authentification réussie")));

        // 2. Open Accounts using Workflow
        mockMvc.perform(post("/api/accounts/open")
                .param("type", "BANK_A")
                .param("id", "BANK_A_REST_001")
                .param("initialBalance", "5000"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/accounts/open")
                .param("type", "MOBILE_MONEY")
                .param("id", "+237671234567")
                .param("initialBalance", "1000"))
                .andExpect(status().isOk());

        // 3. Perform Transfer using Builder
        mockMvc.perform(post("/api/transactions/transfer")
                .param("from", "BANK_A_REST_001")
                .param("to", "+237671234567")
                .param("amount", "1000")
                .param("full", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Transaction effectuée")));

        // 4. Verify Account with Decorator
        mockMvc.perform(get("/api/accounts/BANK_A_REST_001")
                .param("debug", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Solde: 4000")));

        // 5. Run Analytics using Visitor
        mockMvc.perform(get("/api/transactions/analytics"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Total commissions")));
    }
}
