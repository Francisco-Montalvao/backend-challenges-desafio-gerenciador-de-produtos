# 🛒 Gerenciador de Produtos API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge&logo=lombok&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License](https://img.shields.io/github/license/Francisco-Montalvao/backend-challenges-desafio-gerenciador-de-produtos?style=for-the-badge)
![GitHub stars](https://img.shields.io/github/stars/Francisco-Montalvao/backend-challenges-desafio-gerenciador-de-produtos?style=for-the-badge)

API desenvolvida em Java 21 + Spring Boot para gerenciar o catálogo de produtos de um e-commerce, com suporte a categorias, validações e tratamento de erros.

---

## 📌 Índice

- [Tecnologias](#-tecnologias)
- [Regras de negócio](#-regras-de-negócio)
- [Validações e Exceções](#-validações-e-tratamento-de-exceções)
- [Endpoints](#-endpoints)
- [Como Executar](#-como-executar)
- [Estrutura do Projeto](#-estrutura-do-projeto)

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok

---

## 📊 Regras de negócio

- Nome de produto e de categoria devem ser únicos
- Preço deve ser maior que zero
- Estoque não pode ser negativo
- `categoria_id` deve referenciar uma categoria existente
- Não é permitido deletar uma categoria que possui produtos vinculados
- O `PUT` substitui o recurso por completo — todos os campos são obrigatórios
- Lista vazia retorna `200` com `[]`, nunca `404`

---

## 🛡️ Validações e Tratamento de Exceções

A API conta com validação estruturada dos dados de entrada via Bean Validation. Caso ocorram erros, um `GlobalExceptionHandler` intercepta e retorna respostas padronizadas.

**Exemplo de erro de validação — `400 Bad Request`:**
```json
{
  "timestamp": "2026-05-27T15:14:38.917029",
  "status": 400,
  "mensagem": "Erro de validação em campos",
  "erros": [
    {
      "campo": "preco",
      "mensagem": "preco tem que ser maior que zero"
    },
    {
      "campo": "estoque",
      "mensagem": "estoque nao pode ser negativo"
    }
  ]
}
```

**Exemplo de erro de conflito — `409 Conflict`:**
```json
{
  "timestamp": "2026-05-27T15:15:15.979381",
  "status": 409,
  "mensagem": "Já existe um produto com o nome Camiseta Azul"
}
```

**Exemplo de não encontrado — `404 Not Found`:**
```json
{
  "timestamp": "2026-05-27T15:13:13.922031",
  "status": 404,
  "mensagem": "produto com id 1 nao encontrado"
}
```

---

## 📑 Endpoints

### Categorias

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `POST` | `/categorias` | Cria uma categoria | `201` / `409` nome duplicado |
| `GET` | `/categorias` | Lista todas | `200` |
| `DELETE` | `/categorias/{id}` | Remove | `204` / `404` / `400` com produtos vinculados |

### Produtos

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `POST` | `/produtos` | Cria um produto | `201` / `400` / `404` |
| `GET` | `/produtos` | Lista todos | `200` |
| `GET` | `/produtos/{id}` | Busca por ID | `200` / `404` |
| `PUT` | `/produtos/{id}` | Atualiza por completo | `200` / `400` / `404` |
| `DELETE` | `/produtos/{id}` | Remove | `204` / `404` |

---

## ✅ Exemplos de uso

### Criar categoria

Request:
```json
{
  "nome": "Eletrônicos"
}
```

Response `201`:
```json
{
  "id": 1,
  "nome": "Eletrônicos",
  "criadoEm": "2026-05-21T10:00:00"
}
```

---

### Criar produto

Request:
```json
{
  "nome": "Camiseta Azul",
  "descricao": "100% algodão, tamanho M",
  "preco": 49.90,
  "estoque": 100,
  "categoria_id": 1
}
```

Response `201`:
```json
{
  "id": 1,
  "nome": "Camiseta Azul",
  "descricao": "100% algodão, tamanho M",
  "preco": 49.90,
  "estoque": 100,
  "categoria": {
    "id": 1,
    "nome": "Eletrônicos"
  },
  "criadoEm": "2026-05-21T10:30:00",
  "atualizadoEm": "2026-05-21T10:30:00"
}
```

---

### Atualizar produto

Request:
```json
{
  "nome": "Camiseta Azul",
  "descricao": "100% algodão, tamanho G",
  "preco": 54.90,
  "estoque": 80,
  "categoria_id": 1
}
```

Response `200`:
```json
{
  "id": 1,
  "nome": "Camiseta Azul",
  "descricao": "100% algodão, tamanho G",
  "preco": 54.90,
  "estoque": 80,
  "categoria": {
    "id": 1,
    "nome": "Eletrônicos"
  },
  "criadoEm": "2026-05-21T10:30:00",
  "atualizadoEm": "2026-05-21T11:00:00"
}
```

---

## ✅ Como executar

### Pré-requisitos

- Java 21+
- PostgreSQL rodando localmente

### Configuração do banco

Crie o banco de dados antes de subir a aplicação:

```sql
CREATE DATABASE gerenciador;
```

As configurações de conexão ficam em `src/main/resources/application-dev.properties`. Ajuste usuário e senha conforme seu ambiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

O Hibernate cria e atualiza as tabelas automaticamente via `ddl-auto=update`.

### Rodar localmente

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

---

## 📂 Estrutura do projeto

```
src/main/java/com/francisco_montalvao/gprodutos/
├── controller/   # Recebe requisições e devolve respostas HTTP
├── service/      # Regras de negócio
├── repository/   # Acesso ao banco via Spring Data JPA
├── model/        # Entidades JPA (Produto, Categoria)
├── dto/          # Objetos de entrada (request) e saída (response)
├── mapper/       # Conversão entre entidade e DTO
└── exception/    # Exceções customizadas e handler global
```

---

🧑‍💻 Desenvolvido por [Francisco Montalvao](https://github.com/Francisco-Montalvao) 🚀

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
