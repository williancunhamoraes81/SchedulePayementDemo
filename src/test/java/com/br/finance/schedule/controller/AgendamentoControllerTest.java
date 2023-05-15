package com.br.finance.schedule.controller;

import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;
import com.br.finance.schedule.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class AgendamentoControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoService service;

    @Test
    void testListAgendamentosSucesso() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/agendamento/lista/{contaOrigem}", "0001")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testTransferenciaSucesso() throws Exception {

        AgendamentoTransferenciaPayload request = AgendamentoTransferenciaPayload.builder()
                .contaOrigem("00001")
                .contaDestino("00002")
                .dataAgendamento(LocalDate.now())
                .dataTransferencia(LocalDate.now())
                .valorOriginalTransferido(new BigDecimal("1000"))
                .build();

        objectMapper.registerModule(new JavaTimeModule());
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/agendamento/transferencia")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk());
    }

}

