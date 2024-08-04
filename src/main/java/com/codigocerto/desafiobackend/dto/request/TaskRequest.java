package com.codigocerto.desafiobackend.dto.request;

import com.codigocerto.desafiobackend.enums.Status;
import com.codigocerto.desafiobackend.enums.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @Size(min = 10, max = 100, message = "Nome da tarefa deve ter no minimo 10 caracteres e no maximo 100")
        @NotBlank
        String name,
        @Size(min = 10, max = 200, message = "Descrição da tarefa deve ter no minimo 10 caracteres e no maximo 200")
        @NotBlank
        String description,
        @ValueOfEnum(enumClass = Status.class, message = "Status é um ENUM, portanto deve ser enviado como 'ADIADA' ou 'EM_ANDAMENTO' ou 'CONCLUIDA'")
        String status) {}
