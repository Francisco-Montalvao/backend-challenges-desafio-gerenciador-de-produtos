# Gerenciador de Produtos

Solução para o desafio **Gerenciador de Produtos (Júnior)** — API REST para gerenciar o catálogo de produtos de um e-commerce, com suporte a categorias, validações e tratamento de erros.

## Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok

## Pré-requisitos

- Java 21+
- PostgreSQL rodando localmente

## Configuração do banco

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

O Hibernate cria/atualiza as tabelas automaticamente (`ddl-auto=update`).

## Como rodar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Endpoints

### Categorias

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/categorias` | Cria uma categoria | 201 / 409 nome duplicado |
| GET | `/categorias` | Lista todas | 200 |
| DELETE | `/categorias/{id}` | Remove | 204 / 404 / 400 com produtos vinculados |

### Produtos

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/produtos` | Cria um produto | 201 / 400 / 404 |
| GET | `/produtos` | Lista todos | 200 |
| GET | `/produtos/{id}` | Busca por ID | 200 / 404 |
| PUT | `/produtos/{id}` | Atualiza por completo | 200 / 400 / 404 |
| DELETE | `/produtos/{id}` | Remove | 204 / 404 |

## Exemplos de uso

### Criar categoria

```bash
curl -X POST http://localhost:8080/categorias \
  -H "Content-Type: application/json" \
  -d '{"nome": "Eletrônicos"}'
```

```json
{
  "id": 1,
  "nome": "Eletrônicos",
  "criadoEm": "2026-05-21T10:00:00"
}
```

### Criar produto

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Camiseta Azul",
    "descricao": "100% algodão, tamanho M",
    "preco": 49.90,
    "estoque": 100,
    "categoria_id": 1
  }'
```

```json
{
  "id": 1,
  "nome": "Camiseta Azul",
  "descricao": "100% algodão, tamanho M",
  "preco": 49.90,
  "estoque": 100,
  "categoria": { "id": 1, "nome": "Eletrônicos" },
  "criadoEm": "2026-05-21T10:30:00",
  "atualizadoEm": "2026-05-21T10:30:00"
}
```

### Atualizar produto

```bash
curl -X PUT http://localhost:8080/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Camiseta Azul",
    "descricao": "100% algodão, tamanho G",
    "preco": 54.90,
    "estoque": 80,
    "categoria_id": 1
  }'
```

## Regras de negócio

- Nome de produto e de categoria devem ser únicos
- Preço deve ser maior que zero; estoque não pode ser negativo
- `categoria_id` deve referenciar uma categoria existente
- Não é permitido deletar uma categoria que possui produtos vinculados
- O PUT substitui o recurso por completo — todos os campos são obrigatórios
- Lista vazia retorna `200` com `[]`, nunca `404`

## Estrutura do projeto

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
