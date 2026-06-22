# Sistema Financeiro Pessoal

API backend para controle financeiro pessoal, desenvolvida como projeto de portfólio para demonstrar arquitetura limpa, segurança com JWT, persistência em PostgreSQL, geração de relatórios em PDF e documentação de API com Swagger/OpenAPI.

## Visão Geral

O sistema permite ao usuário registrar receitas e despesas, acompanhar metas financeiras, consultar um dashboard consolidado e gerar relatórios em PDF com seus lançamentos.

O foco do projeto está em:

- organização em camadas;
- regras de negócio centralizadas em services;
- endpoints enxutos;
- validação de entrada;
- testes unitários e de integração;
- documentação clara para uso e manutenção.

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

- Cadastro e autenticação com JWT
- Registro de receitas e despesas
- Metas financeiras por usuário
- Dashboard com saldo e visão consolidada
- Relatório PDF com os lançamentos autenticados
- API pública documentada com Swagger

## Arquitetura

O backend foi organizado em módulos com separação explícita de responsabilidades:

- `auth/` autenticação e emissão de token
- `transaction/` lançamentos financeiros
- `goal/` metas financeiras
- `dashboard/` agregações para painel
- `report/` geração de PDF
- `user/` persistência do usuário
- `config/` segurança, JWT e OpenAPI
- `exception/` tratamento centralizado de erros

As regras de negócio ficam concentradas nos serviços. Os controllers apenas recebem a requisição, validam o contrato de entrada e delegam a execução para a camada apropriada.

## Estrutura do Projeto

- `backend/` aplicação Spring Boot
- `docker-compose.yml` infraestrutura local com PostgreSQL
- `.env.example` variáveis de ambiente esperadas
- `mvnw` e `mvnw.cmd` wrapper do Maven

## Como Executar Localmente

1. Copie `.env.example` para `.env`.
2. Suba o banco de dados:
   ```powershell
   docker compose up -d
   ```
3. Inicie a aplicação:
   ```powershell
   .\mvnw.cmd -f backend\pom.xml spring-boot:run
   ```
4. Acesse a documentação da API:
   - `http://localhost:8080/swagger-ui/index.html`

## Endpoints Principais

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

Executar a suíte:

```powershell
.\mvnw.cmd -f backend\pom.xml test
```

Cobertura disponível:

- smoke test de contexto
- testes unitários de autenticação
- testes unitários de transações
- testes unitários de metas
- teste do dashboard

## Decisões de Projeto

- O sistema foi mantido backend-only para reforçar a proposta técnica do portfólio.
- PostgreSQL é a fonte de verdade.
- JWT é utilizado para proteger os recursos do usuário autenticado.
- O relatório PDF é gerado localmente a partir dos dados persistidos.

## Melhorias Futuras

- incluir frontend dedicado;
- adicionar paginação em listagens;
- ampliar observabilidade com logs estruturados;
- criar migrações versionadas caso o schema evolua com frequência.
