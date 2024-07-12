package com.codigoCerto.desafioBackEnd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;


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
