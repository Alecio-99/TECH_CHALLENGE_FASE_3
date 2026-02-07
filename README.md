# Tech Challenge – Fase 3
**Pós-Tech – FIAP**

## 📌 Descrição do Projeto

Este projeto foi desenvolvido como parte do **Tech Challenge – Fase 3**, com o objetivo de criar um backend **modular, seguro e escalável**, voltado para um **ambiente hospitalar**, contemplando:

- Agendamento de consultas
- Controle de acesso por perfil de usuário
- Consulta de histórico médico via GraphQL
- Comunicação assíncrona entre serviços utilizando RabbitMQ

A aplicação foi estruturada seguindo boas práticas de arquitetura, separação de responsabilidades e segurança.

---

## 🏗️ Arquitetura da Solução

O sistema foi implementado utilizando **Maven Multi-Module**, simulando uma arquitetura de microserviços.

### 📦 Módulos do Projeto
```txt
TECH_CHALLENGE_FASE_3
│
├── agendamento-service
│ └── Serviço responsável pelo agendamento e consulta de histórico
│
├── notificacao-service
│ └── Serviço responsável pelo envio de notificações
│
└── pom.xml (parent)
```

### 🔹 agendamento-service
Responsável por:
- Autenticação e autorização
- Agendamento de consultas
- Edição de consultas
- Consulta de histórico via REST e GraphQL
- Publicação de eventos no RabbitMQ

### 🔹 notificacao-service
Responsável por:
- Consumo de mensagens do RabbitMQ
- Processamento de notificações (simulado via log)

---

## 🔐 Segurança (Spring Security)

A aplicação utiliza **Spring Security com autenticação básica**.

### Perfis de Usuário

| Perfil | Permissões |
|------|-----------|
| **MÉDICO** | Visualizar e editar histórico de consultas |
| **ENFERMEIRO** | Registrar consultas e visualizar histórico |
| **PACIENTE** | Visualizar apenas suas próprias consultas |

A autorização é feita utilizando `@PreAuthorize` nos endpoints REST e GraphQL.

---

## 🌐 API REST – Agendamento de Consultas

### Criar consulta

POST /consultas

**Acesso:** Médico, Enfermeiro

### Editar consulta

PUT /consultas/{id}

**Acesso:** Médico

### Listar histórico geral

GET /consultas

**Acesso:** Médico, Enfermeiro

### Listar consultas do paciente logado

GET /consultas/meu

**Acesso:** Paciente

---

## 🧩 GraphQL – Histórico de Consultas

O GraphQL foi implementado para permitir consultas flexíveis ao histórico médico.

### Endpoint

POST /graphql


### Queries disponíveis

#### Histórico completo (Médico / Enfermeiro)
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

####  Histórico do paciente logado
```graphql
query {
    consultasPorPaciente {
        id
        dataHora
        status
    }
}
```

#### Consultas futuras
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

#### 📨 Comunicação Assíncrona – RabbitMQ

A comunicação entre os serviços é feita de forma assíncrona utilizando RabbitMQ.

Fluxo de Mensagens:

1. O agendamento-service publica um evento quando uma consulta é criada ou editada. 
2. O notificacao-service consome a mensagem. 
3. A notificação é processada (simulada via log).

Essa abordagem garante:

1. Desacoplamento entre serviços 
2. Escalabilidade 
3. Comunicação assíncrona

---

#### 🛠️ Tecnologias Utilizadas

- Java 17 
- Spring Boot 
- Spring Security 
- Spring GraphQL 
- Spring AMQP (RabbitMQ)
- Maven Multi-Module 
- H2 Database 
- Docker (para RabbitMQ) --Pendente-- 
- GraphQL 
- REST APIs

#### Como Executar o Projeto

Pré-requisitos 
- Java 17+ 
- Maven 
- Docker (para RabbitMQ)

Subir RabbitMQ
```bash
docker run -d --name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
rabbitmq:3-management
```
Build do projeto
```bash
mvn clean install
```
Executar os serviços

- Agendamento
```bash
cd agendamento-service
mvn spring-boot:run
```

- Notificação
```bash
cd notificacao-service
mvn spring-boot:run
```
---
#### 🧪 Testes

- Collections do Postman podem ser utilizadas para testar os endpoints REST. 
- Queries GraphQL podem ser testadas via Postman ou GraphQL Playground. 
- Logs do notificacao-service demonstram o recebimento de mensagens via RabbitMQ.

---
#### 📄 Considerações Finais

Este projeto demonstra a aplicação prática de:

- Segurança em aplicações Java 
- Arquitetura modular 
- Comunicação assíncrona 
- GraphQL para consultas flexíveis 
- Boas práticas com Spring Boot

Atendendo integralmente aos requisitos propostos no Tech Challenge – Fase 3.

---

#### Autores:
- Giovana Leite Scalabrini
- Alecio 

