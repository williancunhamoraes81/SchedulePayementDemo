# SchedulePayementDemo

Aplicação Demo para agendamentro de transferências
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


#### 📋 Clonar repositório

```
git clone https://github.com/williancunhamoraes81/SchedulePayementDemo.git
```

#### 🚢 Executar serviços necessários

Essa API utiliza o H2 como banco de dados. Disponível apenas para utilização localhost:8200
http://localhost:8200/api/h2-console


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

<b>CURL para realizar uma pesquisa de agendameto</b>

curl --location 'localhost:8200/api/agendamento/lista/0002' \
--data ''

#### ⚙️Compilar a aplicação

```
mvn clean install
mvn compile
```

