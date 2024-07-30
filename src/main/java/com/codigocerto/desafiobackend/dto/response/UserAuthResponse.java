package com.codigocerto.desafiobackend.dto.response;

import lombok.Builder;

@Builder
public record UserAuthResponse(Long id, String token) {
}
