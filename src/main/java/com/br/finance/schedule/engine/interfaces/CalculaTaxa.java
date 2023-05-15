package com.br.finance.schedule.engine.interfaces;

import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;

import java.math.BigDecimal;

public interface CalculaTaxa {

    BigDecimal calcula(AgendamentoTransferenciaPayload payload);

    void setProximo(CalculaTaxa proximo);

}
