package com.codigoCerto.desafioBackEnd.dto.response;

import lombok.Builder;

@Builder
public record TaskUserResponse(Long id, Long taskId, Long userId) {
}
