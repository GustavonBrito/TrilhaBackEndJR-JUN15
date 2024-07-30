package com.codigocerto.desafiobackend.entity;

import com.codigocerto.desafiobackend.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task Entity")
public class TaskEntity {
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;
    @Schema(description = "Name of the task", example = "Api Rest")
    private String name;
    @Schema(description = "Description of the task", example = "Make a API Rest")
    private String description;
    @Schema(description = "Status of the activity", example = "EM_ANDAMENTO")
    private Status status;
    @Schema(description = "TimesStamp of Creation", example = "2024-07-20T04:10:52.614+00:00")
    private Timestamp createdAt;
    @Schema(description = "TimeStamp of the Update", example = "2024-07-20T04:10:52.614+00:00")
    private Timestamp updatedAt;
}
