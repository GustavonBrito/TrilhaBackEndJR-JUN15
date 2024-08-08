package com.codigocerto.desafiobackend.utils;

public class ApiResponsesExample {



    public static final String TASK_LIST = """
        [
           {
            "id": 1,
            "name": "Criação de API Rest",
            "description": "Criar uma API REST conectando ao banco SQLITE",
            "status": "EM_ANDAMENTO",
            "createdAt": "2024-07-24T16:21:55.028+00:00",
            "updatedAt": "2024-07-24T16:21:55.028+00:00"
           },
           {
            "id": 2,
            "name": "Criação de API Rest",
            "description": "Criar uma API REST conectando ao banco SQLITE",
            "status": "CONCLUIDA",
            "createdAt": "2024-07-24T16:21:55.028+00:00",
            "updatedAt": "2024-07-24T16:21:55.028+00:00"
           }
        ]""";

    public static final String UNIQUE_TASK = """
        {
            "id": 1,
            "name": "Criação de API Rest",
            "description": "Criar uma API REST conectando ao banco SQLITE",
            "status": "EM_ANDAMENTO",
            "createdAt": "2024-07-24T16:21:55.028+00:00",
            "updatedAt": "2024-07-24T16:21:55.028+00:00"
        }""";

    public static final String UNIQUE_TASK_USER = """
        {
        "id": 1,
        "taskId": 1,
        "userId": 1,
        "createdAt": "2024-07-24T16:21:55.028+00:00",
        "updatedAt": "2024-07-24T16:21:55.028+00:00"
        }""";

    public static final String USER_LIST = """
        [
           {
            "id": 2,
            "name": "Gustavo",
            "email": "gustvo@gmail.com",
            "createdAt": "2024-07-25T01:55:58.690+00:00",
            "updatedAt": "2024-07-25T01:55:58.691+00:00"
           },
           {
            "id": 2,
            "name": "Gustavo",
            "email": "gustvo@gmail.com",
            "createdAt": "2024-07-25T01:55:58.690+00:00",
            "updatedAt": "2024-07-25T01:55:58.691+00:00"
           }
        ]""";

        public static final String UNIQUE_USER = """
        {
        "id": 2,
        "name": "Gustavo",
        "email": "gustvo@gmail.com",
        "createdAt": "2024-07-25T01:55:58.690+00:00",
        "updatedAt": "2024-07-25T01:55:58.691+00:00"
        }
        """;

        public static final String LOGIN_USER = """
        {
        "id": 1,
        "token": "bearer token"
        }
        """;

        public static final String BAD_REQUEST = """
                {
                    "type": "errors/string",
                    "title": "string",
                    "status": 400,
                    "detail": "string",
                    "instance": "string",
                    "timeStamp": "2024-08-08T01:58:40.79825025"
                }
        """;


}
