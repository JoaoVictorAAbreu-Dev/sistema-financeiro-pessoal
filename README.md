# Sistema Financeiro Pessoal

Sistema financeiro pessoal backend-only construído para portfólio, com foco em receitas, despesas, metas, dashboard, relatórios PDF e documentação de API.

## Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Docker
- Maven
- Swagger/OpenAPI
- PDFBox
- JUnit 5
- Mockito

## Funcionalidades

- Cadastro e login com JWT
- Registro de receitas e despesas
- Metas financeiras por usuário
- Dashboard com saldo e contagem de metas
- Relatório PDF com os lançamentos do usuário
- API documentada com Swagger

## Estrutura

- `backend/` API Spring Boot
- `docker-compose.yml` banco PostgreSQL local
- `.env.example` variáveis de ambiente
- `mvnw` e `mvnw.cmd` para build independente do Maven instalado no PATH

## Como rodar

1. Copie `.env.example` para `.env`.
2. Suba o PostgreSQL:
   ```powershell
   docker compose up -d
   ```
3. Execute a API:
   ```powershell
   .\mvnw.cmd -f backend\pom.xml spring-boot:run
   ```
4. Acesse a documentação:
   - `http://localhost:8080/swagger-ui/index.html`

## Endpoints principais

- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/transactions/income`
- `POST /api/transactions/expense`
- `GET /api/transactions`
- `POST /api/goals`
- `GET /api/goals`
- `GET /api/dashboard`
- `GET /api/reports/pdf`

## Testes

Executar:

```powershell
.\mvnw.cmd -f backend\pom.xml test
```

Cobertura atual:
- smoke test de contexto
- testes unitários de auth
- testes unitários de transações
- testes unitários de metas
- teste de dashboard

## Observações

- Este projeto é backend-only por design.
- O objetivo é demonstrar arquitetura, validação, segurança, persistência e documentação de API.
- O relatório PDF é gerado localmente a partir dos lançamentos do usuário autenticado.
