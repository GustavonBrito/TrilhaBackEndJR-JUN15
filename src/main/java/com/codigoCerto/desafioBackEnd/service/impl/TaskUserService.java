package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.TaskUserMapper;
import com.codigoCerto.desafioBackEnd.dto.request.TaskUserRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskUserResponse;
import com.codigoCerto.desafioBackEnd.entity.TaskUserEntity;
import com.codigoCerto.desafioBackEnd.repository.impl.TaskUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskUserService {
    private final TaskUserRepository taskUserRepository;

    public TaskUserResponse saveTaskUser(TaskUserRequest taskUserRequest){
        TaskUserEntity taskUserEntity = TaskUserMapper.transformRequestToEntity(taskUserRequest);
        TaskUserEntity taskUserCreated = this.taskUserRepository.save(taskUserEntity);
        return TaskUserMapper.transformEntityToResponse(taskUserCreated);
    }
}
