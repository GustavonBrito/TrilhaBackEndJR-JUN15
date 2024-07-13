package com.codigoCerto.desafioBackEnd.dto.response;

import lombok.Builder;

@Builder
public record UserEditProfileResponse(Long id, String name, String email) {
}
