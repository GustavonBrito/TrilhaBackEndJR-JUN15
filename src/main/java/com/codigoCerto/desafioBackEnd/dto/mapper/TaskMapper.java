package com.codigoCerto.desafioBackEnd.dto.mapper;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
     public static TaskEntity transformRequestToEntity (TaskRequest taskRequest){
        return TaskEntity.builder()
                .name(taskRequest.name())
                .description(taskRequest.description())
                .status(taskRequest.status())
                .build();
    }

    public static TaskResponse transformEntityToResponse (TaskEntity taskEntity){
        return TaskResponse.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus())
                .createdAt(taskEntity.getCreatedAt())
                .updatedAt(taskEntity.getUpdatedAt())
                .build();
    }
}
