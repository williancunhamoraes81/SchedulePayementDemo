package com.br.finance.schedule.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "AGENDAMENTO_TRANSFERENCIA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorOriginalTransferido;
    private BigDecimal valorFinalCalculado;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

}
