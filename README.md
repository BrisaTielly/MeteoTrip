
---

# MeteoTrip

MeteoTrip é uma API inteligente que sugere lugares para visitar em uma cidade, com base no estado e no tipo de viagem (turismo, negócios, aventura, entre outros). A aplicação oferece a possibilidade de cadastrar cidades, listar as cidades cadastradas, e gerar roteiros personalizados para viagens.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de APIs RESTful.
- **Reactor**: Para suporte a programação reativa com `Mono` e `Flux`.
- **H2 Database**: Banco de dados em memória para armazenamento das cidades.
- **Gemini API**: Utilizada para gerar sugestões de itinerários de viagem.
- **Lombok**: Biblioteca para simplificar a criação de getters, setters e construtores.

## Instalação

### Requisitos

- **Java 17+**
- **Maven**
- **IDE de sua preferência** (IntelliJ, Eclipse, VS Code, etc.)

### Passos para rodar o projeto

1. Clone o repositório para sua máquina local:

    ```bash
    git clone https://github.com/BrisaTielly/MeteoTrip.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd MeteoTrip
    ```

3. Compile e execute o projeto com Maven:

    ```bash
    mvn clean spring-boot:run
    ```

4. A API estará disponível em `http://localhost:8080`.

## Endpoints

### 1. Cadastrar Cidade
**POST** `/city/save`

Cadastra uma nova cidade.

**Corpo da Requisição (JSON):**

```json
{
  "name": "São Paulo",
  "state": "SP",
  "tripType": "TOURISM"
}
```

**Resposta:**

```json
{
  "id": 1,
  "name": "São Paulo",
  "state": "SP",
  "tripType": "TOURISM"
}
```

### 2. Listar Todas as Cidades
**GET** `/city/list`

Retorna todas as cidades cadastradas.

**Resposta:**

```json
[
  {
    "id": 1,
    "name": "São Paulo",
    "state": "SP",
    "tripType": "TOURISM"
  },
  ...
]
```

### 3. Buscar Cidade por ID
**GET** `/city/list/{id}`

Retorna a cidade com o ID especificado.

**Exemplo de Requisição:** `/city/list/1`

**Resposta:**

```json
{
  "id": 1,
  "name": "São Paulo",
  "state": "SP",
  "tripType": "TOURISM"
}
```

### 4. Atualizar Cidade
**PUT** `/city/update/{id}`

Atualiza os dados de uma cidade existente.

**Corpo da Requisição (JSON):**

```json
{
  "name": "Rio de Janeiro",
  "state": "RJ",
  "tripType": "ADVENTURE"
}
```

**Resposta:**

```json
{
  "id": 1,
  "name": "Rio de Janeiro",
  "state": "RJ",
  "tripType": "ADVENTURE"
}
```

### 5. Deletar Cidade
**DELETE** `/city/delete/{id}`

Deleta a cidade com o ID especificado.

**Exemplo de Requisição:** `/city/delete/1`

**Resposta:**

```json
"Deletado com sucesso"
```

### 6. Gerar Roteiro de Viagem
**GET** `/travel/generate`

Gera sugestões de lugares para visitar na cidade especificada, com base no tipo de viagem.

## Como Funciona

- **Cadastro de Cidades**: O usuário pode cadastrar cidades, fornecendo o nome da cidade, o estado e o tipo de viagem (por exemplo, turismo, negócios, aventura).
- **Geração de Roteiros**: A cada solicitação de geração de itinerário, o sistema busca a cidade cadastrada e, com base no tipo de viagem, gera sugestões personalizadas.
- **Banco de Dados**: Utiliza o H2 para persistir as cidades, permitindo adicionar, listar, atualizar e excluir cidades cadastradas.

## Tipos de Viagem

Os tipos de viagem disponíveis para as cidades são:

- **TOURISM**: Viagem voltada para turismo.
- **BUSINESS**: Viagem voltada para negócios.
- **ADVENTURE**: Viagem voltada para aventura.
- **HONEYMOON**: Viagem para lua de mel.
- **FAMILY**: Viagem para a família.
- **CULTURAL**: Viagem para imersão cultural.
- **GASTRONOMIC**: Viagem voltada para gastronomia.

## Variáveis de Ambiente

- **API_KEY**: A chave necessária para interagir com a Gemini API para geração de roteiros.

---
