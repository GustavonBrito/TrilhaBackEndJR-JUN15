package com.codigoCerto.desafioBackEnd.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserEditProfileRequest (

        @Size(min = 2,max = 60, message = "Nome não deve ter menos que dois caracteres e mais que 60 caracteres")
        String name,
        @Email
        String email){
}
