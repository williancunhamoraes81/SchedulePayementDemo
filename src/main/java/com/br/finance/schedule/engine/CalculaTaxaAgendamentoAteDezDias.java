package com.br.finance.schedule.engine;

import com.br.finance.schedule.engine.interfaces.CalculaTaxa;
import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class CalculaTaxaAgendamentoAteDezDias implements CalculaTaxa {

    private static final BigDecimal VALOR_TAXA  = new BigDecimal("12.0");
    private static final Long VALOR_DIAS        = 10L;

    private CalculaTaxa proximo;

    @Override
    public BigDecimal calcula(AgendamentoTransferenciaPayload payload) {
        Long horas = ChronoUnit.DAYS.between(payload.getDataAgendamento(), payload.getDataTransferencia());
        if(horas <= VALOR_DIAS){
            return payload.getValorOriginalTransferido().add(VALOR_TAXA);
        }else{
            return proximo.calcula(payload);
        }
    }

    @Override
    public void setProximo(CalculaTaxa proximo) {
        this.proximo = proximo;
    }
}
