package com.br.finance.schedule.service;

import com.br.finance.schedule.model.entity.AgendamentoEntity;
import com.br.finance.schedule.repository.AgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AgendamentoService.class})
@ExtendWith(SpringExtension.class)
class AgendamentoServiceTest {

    @MockBean
    private AgendamentoRepository repository;

    @Autowired
    private AgendamentoService service;

    @Test
    void testListaBadRequest() {
        when(repository.listaAgendamento(any())).thenReturn(new ArrayList<>());
        ResponseEntity<Object> lista = service.lista(any());

        assertEquals("Nenhum agendamento disponível", lista.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, lista.getStatusCode());
        assertTrue(lista.getHeaders().isEmpty());
        verify(repository).listaAgendamento(any());
    }

    @Test
    void testListaSuccess() {

        List<AgendamentoEntity> listaEntity = new ArrayList<>();
        AgendamentoEntity entity = AgendamentoEntity.builder()
                .contaOrigem("00001")
                .contaDestino("00002")
                .dataAgendamento(LocalDate.now())
                .dataTransferencia(LocalDate.now())
                .valorOriginalTransferido(new BigDecimal("1000"))
                .valorFinalCalculado(new BigDecimal("1001"))
                .id(1L)
                .build();

        listaEntity.add(entity);

        when(repository.listaAgendamento(any())).thenReturn(listaEntity);
        ResponseEntity<Object> lista = service.lista(any());

        assertEquals(HttpStatus.OK, lista.getStatusCode());
        verify(repository).listaAgendamento(any());
    }

}

