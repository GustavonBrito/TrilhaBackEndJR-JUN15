package com.codigocerto.desafiobackend.dto.response;

import lombok.Builder;

@Builder
public record TaskUserResponse(Long id, Long taskId, Long userId) {
}
