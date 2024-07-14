package com.codigoCerto.desafioBackEnd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String confirmedPassword;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
