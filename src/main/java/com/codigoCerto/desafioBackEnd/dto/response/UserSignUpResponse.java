package com.codigoCerto.desafioBackEnd.dto.response;

import lombok.Builder;

@Builder
public record UserSignUpResponse(String name, String email) {
}
