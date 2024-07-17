package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.TaskMapper;
import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.entity.TaskEntity;
import com.codigoCerto.desafioBackEnd.repository.impl.TaskRepository;
import com.codigoCerto.desafioBackEnd.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponse createTask(TaskRequest tasksRequest) {
        TaskEntity taskEntity = TaskMapper.transformRequestToEntity(tasksRequest);
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return TaskMapper.transformEntityToResponse(savedEntity);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return List.of();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return null;
    }

    @Override
    public TaskResponse updateTaskById(Long id) {
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
    }
}
