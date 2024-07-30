package com.codigocerto.desafiobackend.dto.request;

import com.codigocerto.desafiobackend.utils.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(
        @NotBlank
        @Size(min = 2, max = 40, message = "O nome deve ter no minimo dois caracteres e no máximo 40")
        String name,
        @NotBlank
        @Email(message = "O endereço de email deve ser valido")
        String email,
        @NotBlank
        @Pattern(regexp = Regex.PASSWORD,
                message = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra minúscula, um dígito e um caractere especial"
        )
        String password,
        @NotBlank
        @Pattern(regexp = Regex.PASSWORD,
                message = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra minúscula, um dígito e um caractere especial"
        )
        String confirmedPassword) {
}
