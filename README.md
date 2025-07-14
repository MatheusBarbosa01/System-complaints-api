# Backend - API do Sistema de Gerenciamento de Reclamações

Este repositório contém o backend da aplicação de gerenciamento de reclamações, desenvolvido com **Spring Boot 3.5.3**, **Java 17** e **PostgreSQL 16.9**. Ele oferece endpoints REST seguros com autenticação baseada em **JWT**.

---

## Tecnologias

- Java 17
- Spring Boot 3.5.3
- Spring Security + JWT
- PostgreSQL 16.9
- Flyway (migrations)
- Lombok

---

## Configurações do Banco de Dados

A aplicação depende de um banco PostgreSQL com as seguintes credenciais:

- Banco: `complaints_db`
- Usuário: `postgres`
- Senha: `123456`
- Porta: `5432`

URL JDBC usada: 
`jdbc:postgresql://localhost:5432/complaints_db`

---

## Subindo o banco com Docker

Você pode subir o banco localmente com:

```bash
docker run --name postgres-db -e POSTGRES_DB=complaints_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 -p 5432:5432 -v pgdata:/var/lib/postgresql/data -d postgres:16 

```
---

## Rodando o Backend Localmente

- Build e execução com Maven
```bash
./mvnw clean package -DskipTests

java -jar target/system-0.0.1-SNAPSHOT.jar
```
- Ou execute direto com o Spring Boot:
```bash
./mvnw spring-boot:run
```


