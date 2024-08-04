package com.codigocerto.desafiobackend.dto.mapper;

import com.codigocerto.desafiobackend.dto.request.TaskRequest;
import com.codigocerto.desafiobackend.dto.response.TaskResponse;
import com.codigocerto.desafiobackend.entity.TaskEntity;
import com.codigocerto.desafiobackend.enums.converter.StatusConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private static StatusConverter statusConverter;

    @Autowired
    public void setStatusConverter(StatusConverter statusConverter) {
        TaskMapper.statusConverter = statusConverter;
    }

    public static TaskEntity transformRequestToEntity (TaskRequest taskRequest){
        return TaskEntity.builder()
                .name(taskRequest.name())
                .description(taskRequest.description())
                .status(statusConverter.convertToEntityAttribute(taskRequest.status()))
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
