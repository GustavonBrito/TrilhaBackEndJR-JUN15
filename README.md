# Código-Certo-Back-End
![ChatGPT](https://img.shields.io/badge/chatGPT-74aa9c?style=for-the-badge&logo=openai&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![YAML](https://img.shields.io/badge/yaml-%23ffffff.svg?style=for-the-badge&logo=yaml&logoColor=151515)
![Red Hat](https://img.shields.io/badge/Red%20Hat-EE0000?style=for-the-badge&logo=redhat&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
## Índice

- Endereço do Swagger : http://localhost:8080/swagger-ui/index.html#/
- [Rota API Usuários](#usuarios)
- [Rota API Tarefas](#tarefas)
- [Clonando o Projeto](#clonando-o-projeto)
- [Pré requisitos](#pré-requisitos)
- [Licença](#licença)

## Introdução

Este projeto tem como objetivo desenvolver uma API RESTful para gerenciamento de tarefas, proporcionando funcionalidades de CRUD (Create, Read, Update, Delete) de tarefas, autenticação de usuários e armazenamento dos dados em um banco de dados (SQLITE).

## Rotas da API

### Usuarios

#### Criar Usuário
  - **Rota:** `POST localhost:8080/users/signUp`
  - **Payload:**
    ```json
    {
      "id": 1,
      "name": "string",
      "email": "string",
      "password": "string",
      "confirmedPassword": "string"
    }
    ```
  - **Resposta:**
    ```http 
    Response: 201
    ```
    ```json
    {
      "id": 1,
      "name": "string",
      "email": "string",
      "password": "string",
      "confirmedPassword": "string"
    }
    ```

#### Logar Usuário
  - **Rota:** `POST localhost:8080/users/signIn`
  - **Payload:**
    ```json
    {
      "id": 1,
      "email": "string",
      "password": "string"
    }
    ```
  - **Resposta:**
    ```http 
    Response: 201
    ```

#### Listar Usuários
  - **Rota:** `GET localhost:8080/users`
  - **Autenticação:** O usuário precisa estar logado para fazer essa requisição. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
    ```
  - **Response:**
    ```http
    Status: 200
    ```
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
    ```http
    Status: 200
    ```
    ```json
     {
      "id": 1,
      "name": "John Doe",
      "email": "john.doe@example.com",
      "createdAt": "2023-01-01T12:00:00Z",
      "updatedAt": "2023-01-02T12:00:00Z"
     }
    ```
      
#### Atualizar todos os atributos do Usuário
  - **Rota:** `PUT localhost:8080/users/{id}`
  - **Autenticação:** O usuário precisa estar logado para fazer essa alteração. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
    ```
  - **Payload:**
    ```json
    {
     "id": 1,
     "name": "string",
     "email": "string",
     "password": "string",
     "confirmedPassword": "string"
    }
    ```
  - **Response:**
    ```http
    Status: 200
    ```
    ```json
    {
    "id": 1,
    "name": "string",
    "email": "string",
    "createdAt": "2023-01-01T12:00:00Z",
    "updatedAt": "2023-01-02T12:00:00Z"
    }
    ```
#### Atualizar os atributos do Usuário em partes
  - **Rota:** `PATCH localhost:8080/users/{id}`
  - **Autenticação:** O usuário precisa estar logado para fazer essa alteração. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
  - **Cabeçalho de Autenticação:**
    ```http
    Authorization: Bearer {token}
  - **Payload:**
    ```json
    {
      "name": "string"
    }
  - **Response:**
    ```http
    Status: 200
    ```
    ```json
      {
      "id": 1,
      "name": "string",
      "email": "string",
      "createdAt":"2023-01-01T12:00:00Z",
      "updatedAt": "2023-01-02T12:00:00Z"
      }
    ```
#### Deletar Usuário
  - **Rota:** `DELETE localhost:8080/users/{id}`
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
  - **Rota:** `POST localhost:8080/tasks`
  - **Autenticação:** O usuário precisa estar logado para fazer essa criação. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
  ```
  ```json
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string"
   }
  ```
- **Response:**
  ```http
  Status: 201
  ```
    ```json
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   }
  ```
#### Listar Tarefas
- **Rota:** `GET localhost:8080/tasks`
- **Autenticação:** O usuário precisa estar logado para fazer essa requisição. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
  ```
- **Response:**
  ```http
  Status: 200
  ```
  ```json
  [
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   },
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   }
  ]
  ```

#### Buscar Tarefa por ID
- **Rota:** `GET localhost:8080/tasks/{id}`
- **Autenticação:** O usuário precisa estar logado para fazer essa requisição. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
  ```
- **Response:**
  ```http
  Status: 200
  ```
  ```json
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   }
  ```

#### Atualizar todos os atributos da Tarefa
- **Rota:** `PUT localhost:8080/tasks/{id}`
- **Autenticação:** O usuário precisa estar logado para fazer essa alteração. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
  ```
- **Response:**
  ```http
  Status: 200
  ```
  ```json
   {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   }
  ```
  
#### Atualizar atributos da Tarefa em partes
- **Rota:** `PATCH localhost:8080/tasks/{id}`
- **Autenticação:** O usuário precisa estar logado para fazer essa alteração. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
- **Payload:**
  ```json
   {
   "name": "string"
   }
- **Response:**
  ```http
  Status: 200
  ```
  ```json  
  {
   "id": 1,
   "name": "string",
   "description": "string",
   "status": "string",
   "createdAt": "2023-01-01T12:00:00Z",
   "updatedAt": "2023-01-02T12:00:00Z"
   }
  ```
#### Deletar Tarefa
- **Rota:** `DELETE localhost:8080/task/{id}`
- **Autenticação:** O usuário precisa estar logado para fazer essa exclusão. O token JWT (JSON Web Token) gerado durante o login deve ser incluído no cabeçalho da requisição.
- **Cabeçalho de Autenticação:**
  ```http
  Authorization: Bearer {token}
- **Response:**
  ```http
  Status: 200
  ```
  
## Clonando o Projeto
- **Clonando o projeto pra sua máquina**
  ```
  git clone https://github.com/GustavonBrito/TrilhaBackEndJR-JUN15.git
  ```
- **Navegar até o diretório do projeto** 
  ```
  cd nome-do-repositorio
  ```
  
## Pré requisitos

#### Java Development Kit (JDK)

- [Download do JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Certifique-se de que o JAVA_HOME esteja configurado no seu sistema.**

#### Maven

- **Utilize o maven para gerenciar as dependencias do projeto**
- [Instruções de Instalação do Maven](https://maven.apache.org/install.html)

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [MIT](LICENSE) para mais detalhes.
