package com.codigoCerto.desafioBackEnd.dto.response;

import com.codigoCerto.desafioBackEnd.enums.Status;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record TaskResponse(Long id, String name, String description, Status status, Timestamp createdAt, Timestamp updatedAt) {}

