package com.br.finance.schedule.model.payload;

import com.br.finance.schedule.model.entity.AgendamentoEntity;
import com.br.finance.schedule.validator.config.FieldNotMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNotMatch(first = "contaOrigem", second = "contaDestino", message = "As contas não podem ser iguais ou nulas")
public class AgendamentoTransferenciaPayload {

    private String contaOrigem;
    private String contaDestino;

    @DecimalMin(value = "0.01", message = "Valor de transferência não pode ser menor que 1 centavo")
    private BigDecimal valorOriginalTransferido;

    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;

    public AgendamentoEntity buildPayloadToEntity(AgendamentoTransferenciaPayload payload,
                                                  BigDecimal valorFinalCalculado){
        return AgendamentoEntity.builder()
                .contaOrigem(payload.contaOrigem)
                .contaDestino((payload.contaDestino))
                .dataTransferencia(payload.dataTransferencia)
                .dataAgendamento(payload.dataAgendamento)
                .valorOriginalTransferido(payload.valorOriginalTransferido)
                .valorFinalCalculado(valorFinalCalculado)
                .build();
    }

}
