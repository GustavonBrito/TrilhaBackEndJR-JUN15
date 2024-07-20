package com.codigoCerto.desafioBackEnd.entity;

import com.codigoCerto.desafioBackEnd.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    Long id;

    String name;

    String description;

    Status status;

    Timestamp createdAt;

    Timestamp updatedAt;


}
