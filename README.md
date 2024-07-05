# Código-Certo-Back-End

## Índice

- [Introdução](#introdução)
- [Rotas da API](#rotas-da-api)
- [Usuários](#usuários)
- [Tarefas](#tarefas)
- [Clonando o Projeto](#clonando-o-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Licença](#licença)

## Introdução

Este projeto tem como objetivo desenvolver uma API RESTful para gerenciamento de tarefas, proporcionando funcionalidades de CRUD (Create, Read, Update, Delete) de tarefas, autenticação de usuários e armazenamento dos dados em um banco de dados.

## Rotas da API

### Usuários

#### Criar Usuário
  - **Rota:** `POST localhost:8080/usersSignUp`
  - **Payload:**
    ```json
    {
      "name": "string",
      "email": "string",
      "password": "string",
      "confirmedPassword": "string"
    }
  - **Resposta:**
    ```http 
    - Reponse: 200
    ```
  
- #### Logar Usuário
  - **Rota:** `POST localhost:8080/usersSignIn`
  - **Payload:**
    ```json
    {
      "email": "string",
      "password": "string",
    }
  - **Resposta:**
    ```http 
    - Reponse: 200
    ```

#### Listar Usuários
  - **Rota:** `GET localhost:8080/users`
  - **Autenticação:** O usuário precisa estar logado para fazer essa requisição. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
    ```
  - **Response:**
    ```json
    [
     {
      "id": 1,
      "name": "John Doe",
      "email": "john.doe@example.com",
      "createdAt": "2023-01-01T12:00:00Z",
      "updatedAt": "2023-01-02T12:00:00Z"
     },
     {
      "id": 2,
      "name": "Jane Smith",
      "email": "jane.smith@example.com",
      "createdAt": "2023-01-05T15:00:00Z",
      "updatedAt": "2023-01-06T15:00:00Z"
     }
    ]
    ```

#### Buscar Usuário por ID
  - **Rota:** `GET localhost:8080/users/{id}`
  - **Autenticação:** O usuário precisa estar logado para fazer essa requisição. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
    ```
  - **Response:**
    ```json
     {
      "id": 1,
      "name": "John Doe",
      "email": "john.doe@example.com",
      "createdAt": "2023-01-01T12:00:00Z",
      "updatedAt": "2023-01-02T12:00:00Z"
     }
    ```

  
#### Atualizar Usuário
  - **Rota:** `localhost:8080/PUT/users/{id}`
  - **Autenticação:** O usuário precisa estar logado para fazer essa alteração. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
  - **Payload:**
    ```json
    {
      "name": "string",
      "email": "string",
      "password": "string",
      "confirmedPassword": "string"
    }

#### Deletar Usuário
- **Rota:** `localhost:8080/DELETE/users/{id}`
  - **Autenticação:** O usuário precisa estar logado para fazer essa exclusão. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
  - **Response:**
    ```http
    Status: 200
    ```

### Tarefas

##### Criar Tarefa
  - **Rota:** `POST /tasks`
  - **Autenticação:** O usuário precisa estar logado para fazer essa criação. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
    ```
    ```json
    {
    "name": "string",
    "description": "string",
     "status": "string"
    }
