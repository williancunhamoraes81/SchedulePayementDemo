package com.br.finance.schedule.engine;

import com.br.finance.schedule.engine.interfaces.CalculaTaxa;
import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class CalculaTaxaAgendamentoEntreDezEVinte implements CalculaTaxa {

    private static final BigDecimal PORCENTAGEM_TAXA    = new BigDecimal("0.082");
    private static final Long VALOR_DIAS_DE             = 10L;
    private static final Long VALOR_DIAS_ATE            = 20L;

    private CalculaTaxa proximo;

    @Override
    public BigDecimal calcula(AgendamentoTransferenciaPayload payload) {
        Long horas = ChronoUnit.DAYS.between(payload.getDataAgendamento(), payload.getDataTransferencia());
        if(horas > VALOR_DIAS_DE && horas < VALOR_DIAS_ATE){
            BigDecimal porcentagem = payload.getValorOriginalTransferido().multiply(PORCENTAGEM_TAXA);
            return payload.getValorOriginalTransferido().add(porcentagem);
        }else{
            return proximo.calcula(payload);
        }
    }

    @Override
    public void setProximo(CalculaTaxa proximo) {
        this.proximo = proximo;
    }
}
