package com.codigocerto.desafiobackend.dto.response;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record UserSignUpResponse(Long id, String name, String email, Timestamp createdAt, Timestamp updatedAt) {
}
