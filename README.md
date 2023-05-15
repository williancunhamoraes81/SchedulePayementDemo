# SchedulePayementDemo

Aplicação Demo para agendamentro de transferências:

<b>REGRAS </b>
A: Tranferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido;
B: Tranferências até 10 dias da data de agendamento possuem uma taxa de $12.
C: Operações do tipo C tem uma taxa regressiva conforme a data de transferência:
   acima de 10 dias da data de agendamento 8.2%
   acima de 20 dias da data de agendamento 6.9%
   acima de 30 dias da data de agendamento 4.7%
   acima de 40 dias da data de agendamento 1.7%
<br/>

## Tabela 
* AGENDAMENTO_TRANSFERENCIA


### 📚 Recursos Utilizados

- 🌴 GitHub
- ☕ Java 11
- 📦 Maven
- 🐘 H2
- ![image](https://github.com/williancunhamoraes81/SchedulePayementDemo/assets/72080283/6aa9af65-9334-48a7-b3f2-fb4e5cf2bff2)
API REST
- ![image](https://github.com/williancunhamoraes81/SchedulePayementDemo/assets/72080283/c61d3a75-d12a-4a88-a739-c0f852f8bb76)
DESIGN PATTERN - Chain of Responsibility 

<br/><br/>
#### 📋 Clonar repositório

```
git clone https://github.com/williancunhamoraes81/SchedulePayementDemo.git
```
<br/><br/>
#### 🚢 Executar serviços necessários

Essa API utiliza o H2 como banco de dados. Disponível apenas para utilização localhost:8200
http://localhost:8200/api/h2-console
<br/><br/>

#### 🚢 Utilizando API
<b>CURL para realizar uma transferência</b>
<br/>
curl --location 'localhost:8200/api/agendamento/transferencia' \
--header 'Content-Type: application/json' \
--data '{
   "contaOrigem":"0002",
   "contaDestino":"0001",   
   "valorOriginalTransferido":1030,
   "dataTransferencia":"2023-07-16",
   "dataAgendamento": "2023-05-15"
}'
<br/>
<br/>

<b>CURL para realizar uma pesquisa de agendameto</b>
curl --location 'localhost:8200/api/agendamento/lista/0002' \
--data ''
<br>
#### ⚙️Compilar a aplicação
```
mvn clean install
mvn compile
```

