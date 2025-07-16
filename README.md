# Processo Seletivo Logap 2025

Projeto Spring Boot para gerenciamento de clientes, produtos e pedidos, com integração ao banco de dados PostgreSQL.

## Pré-requisitos

- Java 21
- Maven 3.9+
- PostgreSQL

## Configuração do Banco de Dados

Crie um banco chamado `logap` no PostgreSQL e configure o usuário e senha conforme o arquivo [`src/main/resources/application.properties`](src/main/resources/application.properties):

```
spring.datasource.url=jdbc:postgresql://localhost:5432/logap
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Como rodar o projeto

1. Clone o repositório:

   ```sh
   git clone https://github.com/marcojrdev/Back-end-processo-seletivo-logap-2025
   cd processo-seletivo-logap-2025
   ```

2. Instale as dependências e rode o projeto:

   ```sh
   ./mvnw spring-boot:run
   ```

   Ou, se estiver no Windows:

   ```sh
   mvnw.cmd spring-boot:run
   ```

3. Acesse a API em: [http://localhost:8080](http://localhost:8080)

## Endpoints principais

- `/api/cliente` - Gerenciamento de clientes
- `/api/produto` - Gerenciamento de produtos
- `/api/pedidos` - Gerenciamento de pedidos
- `/api/relatorios` - Relatórios de vendas, pedidos pendentes e clientes ativos
- `/api/vogal` - Serviço para encontrar vogais em palavras


## Observações

- O projeto utiliza Lombok, certifique-se de instalar o plugin Lombok em sua IDE.
- O frontend pode ser integrado via CORS com `http://localhost:4200`.

---
