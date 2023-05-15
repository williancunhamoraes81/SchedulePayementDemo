package com.br.finance.schedule.engine;

import com.br.finance.schedule.engine.interfaces.CalculaTaxa;
import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class CalculaTaxaAgendamentoMesmoDia implements CalculaTaxa {

    private static final BigDecimal VALOR_TAXA          = new BigDecimal("3.0");
    private static final BigDecimal PORCENTAGEM_TAXA    = new BigDecimal("0.03");
    private static final DateTimeFormatter FORMATTER    = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private CalculaTaxa proximo;

    @Override
    public BigDecimal calcula(AgendamentoTransferenciaPayload payload) {
        if(payload.getDataAgendamento().format(FORMATTER).equals(
                payload.getDataTransferencia().format(FORMATTER))){
            BigDecimal porcentagem = payload.getValorOriginalTransferido().multiply(PORCENTAGEM_TAXA);
            return payload.getValorOriginalTransferido().add(VALOR_TAXA).add(porcentagem);
        }else{
            return proximo.calcula(payload);
        }
    }

    @Override
    public void setProximo(CalculaTaxa proximo) {
        this.proximo = proximo;
    }
}
