# Stock Assist 

Um projeto educacional, para lidar com leitura de arquivos, MongoDB e utilizar APIs externas.

Sistema para auxiliar a manter o estoque cheio. 
 
## Tecnologias Utilizadas

- **Java** `21`
- **Spring Boot** `3.5.9`
- **Spring Data Mongo**
- **MongoDB**
- **OpenFeign**
- **OpenCSV**
- **Docker**
- **Maven**

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter instalado:

- **Java 21** ou superior
- **Docker** e **Docker Compose**
- **Maven** (opcional, pois o projeto inclui o Maven Wrapper)

## Como iniciar o projeto

### 1. Iniciar o banco de dados

Para iniciar o banco de dados MySQL, execute o seguinte comando:

```sh
docker-compose up -d stock_assist_mongodb
```

Este comando irá:
- Criar um container MongoDB chamado `stock_assist_mongodb`
- Expor a porta `27017`

### 2. Executar a aplicação

Você pode executar a aplicação de duas formas:

**Usando Maven Wrapper (recomendado):**

```sh
./mvnw spring-boot:run
```

**Ou usando Maven instalado:**

```sh
mvn spring-boot:run
```

A aplicação será iniciada na porta padrão `8080`.

### 3. Verificar se está funcionando

Após iniciar a aplicação, você pode verificar se está tudo funcionando acessando:

```
http://localhost:8080/health
```
