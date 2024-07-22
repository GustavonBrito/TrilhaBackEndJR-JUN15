package com.codigoCerto.desafioBackEnd.dto.mapper;

import com.codigoCerto.desafioBackEnd.dto.request.TaskUserRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskUserResponse;
import com.codigoCerto.desafioBackEnd.entity.TaskUserEntity;
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
