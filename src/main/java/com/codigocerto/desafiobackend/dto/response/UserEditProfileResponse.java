package com.codigocerto.desafiobackend.dto.response;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record UserEditProfileResponse(Long id, String name, String email, Timestamp createdAt, Timestamp updatedAt) {
}
