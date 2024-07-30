package com.codigocerto.desafiobackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskUserEntity {
    private Long id;

    private Long taskId;

    private Long userId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
