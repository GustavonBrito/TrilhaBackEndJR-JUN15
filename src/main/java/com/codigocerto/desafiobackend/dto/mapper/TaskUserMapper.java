package com.codigocerto.desafiobackend.dto.mapper;

import com.codigocerto.desafiobackend.dto.request.TaskUserRequest;
import com.codigocerto.desafiobackend.dto.response.TaskUserResponse;
import com.codigocerto.desafiobackend.entity.TaskUserEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskUserMapper {

    public static TaskUserEntity transformRequestToEntity (TaskUserRequest taskUserRequest){
        return TaskUserEntity.builder()
                .taskId(taskUserRequest.taskId())
                .userId(taskUserRequest.userId())
                .build();
    }

    public static TaskUserResponse transformEntityToResponse (TaskUserEntity taskUserEntity){
        return TaskUserResponse.builder()
                .id(taskUserEntity.getId())
                .taskId(taskUserEntity.getTaskId())
                .userId(taskUserEntity.getUserId())
                .build();
    }
}
