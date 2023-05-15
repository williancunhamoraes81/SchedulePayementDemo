package com.br.finance.schedule.service;

import com.br.finance.schedule.engine.*;
import com.br.finance.schedule.engine.interfaces.CalculaTaxa;
import com.br.finance.schedule.model.entity.AgendamentoEntity;
import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;
import com.br.finance.schedule.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    private final CalculaTaxa calculaTaxaMesmoDia               = new CalculaTaxaAgendamentoMesmoDia();
    private final CalculaTaxa calculaTaxaAteDezDias             = new CalculaTaxaAgendamentoAteDezDias();
    private final CalculaTaxa calculaTaxaEntreDezEVinte         = new CalculaTaxaAgendamentoEntreDezEVinte();
    private final CalculaTaxa calculaTaxaEntreVinteETrinta      = new CalculaTaxaAgendamentoEntreVinteETrinta();
    private final CalculaTaxa calculaTaxaEntreTrintaEQuarenta   = new CalculaTaxaAgendamentoEntreTrintaEQuarenta();
    private final CalculaTaxa calculaTaxaAcimaDeQuarenta        = new CalculaTaxaAgendamentoAcimaDeQuarenta();

    @Autowired
    AgendamentoRepository repository;

    public ResponseEntity<Object> realizaTransferencia(AgendamentoTransferenciaPayload payload){
        String checkDates = this.checkDates(payload);
        if(checkDates != null){
            return new ResponseEntity<>(checkDates, HttpStatus.BAD_REQUEST);
        }

        BigDecimal valorTransferenciaCalculado = this.organizaFluxoValidacaoTaxa(payload).
                setScale(2, BigDecimal.ROUND_HALF_UP);
        repository.save(payload.buildPayloadToEntity(payload, valorTransferenciaCalculado));
        return new ResponseEntity<>("Valor de transferência realizado: " + valorTransferenciaCalculado, HttpStatus.OK);
    }

    public ResponseEntity<Object> lista(String contaOrigem){

        List<AgendamentoEntity> listAgendamento = repository.listaAgendamento(contaOrigem);
        if(listAgendamento.size() > 0){
            return new ResponseEntity<>(listAgendamento, HttpStatus.OK);
        }
        return new ResponseEntity<>("Nenhum agendamento disponível", HttpStatus.BAD_REQUEST);
    }

    private BigDecimal organizaFluxoValidacaoTaxa(AgendamentoTransferenciaPayload payload){
        calculaTaxaMesmoDia.setProximo(calculaTaxaAteDezDias);
        calculaTaxaAteDezDias.setProximo(calculaTaxaEntreDezEVinte);
        calculaTaxaEntreDezEVinte.setProximo(calculaTaxaEntreVinteETrinta);
        calculaTaxaEntreVinteETrinta.setProximo(calculaTaxaEntreTrintaEQuarenta);
        calculaTaxaEntreTrintaEQuarenta.setProximo(calculaTaxaAcimaDeQuarenta);

        return calculaTaxaMesmoDia.calcula(payload);
    }

    private String checkDates(AgendamentoTransferenciaPayload payload){
        LocalDate today = LocalDate.now();

        if(payload.getDataAgendamento().isBefore(today)){
            return "Data de agendamento não pode ser menor que atual";
        }else if(payload.getDataTransferencia().isBefore(today)){
            return "Data de transferência não pode ser menor que atual";
        }

        return null;
    }

}
