package com.codigoCerto.desafioBackEnd.dto.request;

import com.codigoCerto.desafioBackEnd.infra.utils.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserAuthRequest (
        @Email
        @NotBlank
        String email,
        @Pattern(regexp = Regex.PASSWORD, message = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra minúscula, um dígito e um caractere especial")
        @NotBlank
        String password){
}
