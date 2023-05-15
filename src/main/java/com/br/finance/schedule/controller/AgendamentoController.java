package com.br.finance.schedule.controller;

import com.br.finance.schedule.model.payload.AgendamentoTransferenciaPayload;
import com.br.finance.schedule.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoService service;


    @GetMapping
    public ResponseEntity<Object> getAgendamentos() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/transferencia")
    public ResponseEntity<Object> realizaTransferencia(@Valid @RequestBody AgendamentoTransferenciaPayload payload) {
        Object retorno = service.realizaTransferencia(payload);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping(value = "/lista/{contaOrigem}")
    public ResponseEntity<Object> lista(@PathVariable(value = "contaOrigem") String contaOrigem) {
        Object retorno = service.lista(contaOrigem);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

}
