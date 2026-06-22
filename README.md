<h1>Sistema Financeiro Pessoal</h1>

<p>
  <img src="https://github.com/JoaoVictorAAbreu-Dev/sistema-financeiro-pessoal/actions/workflows/ci.yml/badge.svg" alt="CI" />
  <img src="https://img.shields.io/badge/Java-21-red" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.x-brightgreen" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue" alt="PostgreSQL" />
</p>

<p>
  Backend API for personal finance management. The project demonstrates JWT security, PostgreSQL persistence, dashboard metrics, PDF reports, automated tests and Swagger/OpenAPI documentation.
</p>

<h2>Features</h2>
<ul>
  <li>User registration and login with JWT.</li>
  <li>Income and expense records.</li>
  <li>Financial goals.</li>
  <li>Dashboard with balance, income, expenses, active goals and goal progress.</li>
  <li>PDF financial report.</li>
  <li>Swagger documentation.</li>
</ul>

<h2>Architecture</h2>
<ul>
  <li><code>auth</code>: authentication and token issuing.</li>
  <li><code>transaction</code>: income and expense operations.</li>
  <li><code>goal</code>: financial goals.</li>
  <li><code>dashboard</code>: aggregated metrics.</li>
  <li><code>report</code>: PDF generation.</li>
  <li><code>exception</code>: centralized API errors.</li>
</ul>

<h2>Run Locally</h2>
<pre><code>docker compose up -d
.\mvnw.cmd -f backend\pom.xml spring-boot:run</code></pre>

<h2>Tests</h2>
<pre><code>.\mvnw.cmd -f backend\pom.xml test</code></pre>

<h2>QA Checklist</h2>
<ul>
  <li>Backend tests included.</li>
  <li>GitHub Actions CI configured.</li>
  <li>Swagger available at <code>http://localhost:8080/swagger-ui/index.html</code>.</li>
  <li>Docker Compose configured for PostgreSQL.</li>
  <li>Execution screenshot included.</li>
</ul>

<h2>Preview</h2>
<p>
  <img src="assets/swagger.png" alt="Swagger UI preview" style="max-width: 100%;" />
</p>
