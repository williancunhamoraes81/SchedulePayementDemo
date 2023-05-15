package com.br.finance.schedule.repository;

import com.br.finance.schedule.model.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends CrudRepository<AgendamentoEntity, Long>{

    @Query(value = "SELECT * FROM AGENDAMENTO_TRANSFERENCIA AT WHERE AT.CONTA_ORIGEM = ?1 ORDER BY AT.DATA_TRANSFERENCIA" +
            " DESC", nativeQuery = true)
    public List<AgendamentoEntity> listaAgendamento(String contaOrigem);

}
