package com.br.finance.schedule.engine;

import com.br.finance.schedule.engine.interfaces.CalculaTaxa;
import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class CalculaTaxaAgendamentoAcimaDeQuarenta implements CalculaTaxa {

    private static final BigDecimal PORCENTAGEM_TAXA    = new BigDecimal("0.017");
    private static final Long VALOR_DIAS                = 40L;

    private CalculaTaxa proximo;

    @Override
    public BigDecimal calcula(AgendamentoTransferenciaPayload payload) {
        Long horas = ChronoUnit.DAYS.between(payload.getDataAgendamento(), payload.getDataTransferencia());
        if(horas > VALOR_DIAS){
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
