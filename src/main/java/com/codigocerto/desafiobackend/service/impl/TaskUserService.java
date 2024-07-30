package com.codigocerto.desafiobackend.service.impl;

import com.codigocerto.desafiobackend.dto.mapper.TaskUserMapper;
import com.codigocerto.desafiobackend.dto.request.TaskUserRequest;
import com.codigocerto.desafiobackend.dto.response.TaskUserResponse;
import com.codigocerto.desafiobackend.entity.TaskUserEntity;
import com.codigocerto.desafiobackend.repository.impl.TaskUserRepository;
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
