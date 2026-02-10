# Tech Challenge – Fase 3

---

## Pós-Tech – FIAP

---
### Descrição do Projeto

Este projeto foi desenvolvido como parte do Tech Challenge – Fase 3, com o objetivo de criar um backend modular, seguro e escalável, voltado para um ambiente hospitalar, contemplando:

- Agendamento de consultas 
- Controle de acesso por perfil de usuário 
- Consulta de histórico médico via REST e GraphQL 
- Comunicação assíncrona entre serviços utilizando RabbitMQ 
- Persistência de dados em banco relacional (MySQL)

A aplicação foi estruturada seguindo boas práticas de arquitetura, separação de responsabilidades, segurança e comunicação entre serviços.

---

### Arquitetura da Solução

O sistema foi implementado utilizando Maven Multi-Module, simulando uma arquitetura de microserviços containerizados com Docker.

---

#### Estrutura do Projeto

```txt
TECH_CHALLENGE_FASE_3
│
├── agendamento-service
│ └── Serviço responsável pelo agendamento e histórico de consultas
│
├── notificacao-service
│ └── Serviço responsável pelo envio de notificações
│
├── docker-compose.yml
├── deploy.sh
└── pom.xml (parent)
```
---

#### agendamento-service

Responsável por:
- Autenticação e autorização (Spring Security)
- Agendamento de consultas 
- Edição de consultas 
- Consulta de histórico médico 
- Exposição de APIs REST e GraphQL 
- Persistência de dados em MySQL 
- Publicação de eventos no RabbitMQ

---

#### notificacao-service

Responsável por:
- Consumo de mensagens do RabbitMQ 
- Processamento de notificações de consultas 
- Simulação do envio de lembretes (via log)

---

#### Segurança (Spring Security)

A aplicação utiliza Spring Security com autenticação básica (HTTP Basic), onde cada perfil de Usuário possui permissoes especificas:
- MÉDICO pode > Criar, editar e visualizar consultas 
- ENFERMEIRO pode > Criar consultas e visualizar histórico 
- PACIENTE pode > Visualizar apenas suas próprias consultas

A autorização é aplicada utilizando @PreAuthorize nos endpoints REST e GraphQL.

---

#### API REST – /consultas
- Criar consulta
- Atualizar Consulta
- Visualizar Todas as Consultas
- Vizualizar Consultar por Paciente

> POST /consultas - Acesso permitido a: Médico, Enfermeiro

> PUT /consultas/{id} - Acesso permitido a: Médico

> GET /consultas - Acesso permitido a: Médico, Enfermeiro

> GET /consultas/meu - Acesso permitido a: Paciente

---

#### GraphQL – Histórico de Consultas

O GraphQL foi implementado para permitir consultas flexíveis ao histórico médico.

**Endpoint:**
> POST /graphql

**Queries disponíveis:**

Histórico completo (Médico / Enfermeiro)
```graphql
query {
    consultas {
        id
        medicoId
        pacienteId
        dataHora
        status
    }
}
```

**Histórico do paciente logado:**
```graphql
query {
    consultasPorPaciente {
        id
        dataHora
        status
    }
}
```

**Consultas futuras:**
```graphql
query {
    consultasFuturas {
        id
        dataHora
        status
    }
}
```

---

#### Comunicação Assíncrona – RabbitMQ

A comunicação entre os serviços é feita de forma assíncrona utilizando RabbitMQ.

**Fluxo de Mensagens:**

1. O agendamento-service publica um evento quando uma consulta é criada ou editada. 
2. O notificacao-service consome a mensagem da fila. 
3. A notificação é processada (simulada via log).

**Benefícios da abordagem:**

- Desacoplamento entre serviços 
- Maior escalabilidade 
- Processamento assíncrono

---

### Tecnologias Utilizadas

- Java 17 
- Spring Boot 3 
- Spring Security 
- Spring GraphQL 
- Spring Data JPA 
- Spring AMQP (RabbitMQ)
- MySQL 8 
- Maven Multi-Module 
- Docker & Docker Compose 
- REST APIs 
- GraphQL

---

### Como Executar o Projeto (Docker)

**Pré-requisitos:**

- Docker 
- Docker Compose

**Executar a aplicação:**

Na raiz do projeto, execute:

```bash
chmod +x deploy.sh
./deploy.sh
```

**O script irá:**

- Buildar os projetos com Maven 
- Criar as imagens Docker 
- Subir MySQL, RabbitMQ e os serviços 
- Inicializar toda a aplicação

**Serviços disponíveis:**

> Agendamento Service	http://localhost:8080

> Notificação Service	http://localhost:8081

> RabbitMQ Management	http://localhost:15672

**Credenciais RabbitMQ:**

> Usuario: guest

> Senha: guest

---

### Testes

- Endpoints REST testados via Postman
- Queries GraphQL testadas via Postman 
- Logs do notificacao-service demonstram o consumo das mensagens do RabbitMQ 
- Interface web do RabbitMQ permite visualizar filas e mensagens

---

### Considerações Finais

Este projeto demonstra a aplicação prática de:

- Segurança em aplicações Java 
- Arquitetura modular baseada em microserviços 
- Comunicação assíncrona com mensageria 
- Uso de GraphQL para consultas flexíveis 
- Containerização com Docker 
- Boas práticas com Spring Boot

Atendendo integralmente aos requisitos propostos no Tech Challenge – Fase 3.

---

### Autores

Giovana Leite Scalabrini

Alecio

---