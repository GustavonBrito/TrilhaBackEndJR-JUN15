package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.TaskMapper;
import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.entity.TaskEntity;
import com.codigoCerto.desafioBackEnd.exception.IsIdStoredAtDataBase;
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
        List<TaskEntity> allTasksFoundByRepo = this.taskRepository.findAll();
        return allTasksFoundByRepo.stream().map(TaskMapper::transformEntityToResponse).toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        TaskEntity taskFoundByIdByRepo = this.taskRepository.findById(id);
        if (taskFoundByIdByRepo.getId() == null){
            throw new IsIdStoredAtDataBase("id " + id + " não está cadastrado no sistema");
        }
        return TaskMapper.transformEntityToResponse(taskFoundByIdByRepo);
    }

    @Override
    public TaskResponse updateTaskById(Long id, TaskRequest taskRequest) {
        TaskEntity taskFoundByIdByRepo = this.taskRepository.findById(id);
        if (taskFoundByIdByRepo.getId() == null){
            throw new IsIdStoredAtDataBase("id " + id + " não está cadastrado no sistema");
        }
        TaskEntity taskEntity = TaskMapper.transformRequestToEntity(taskRequest);

        return TaskMapper.transformEntityToResponse(this.taskRepository.updateById(id,taskEntity));
    }

    @Override
    public void deleteTaskById(Long id) {
        TaskEntity taskFoundByIdByRepo = this.taskRepository.findById(id);
        if (taskFoundByIdByRepo.getId() == null){
            throw new IsIdStoredAtDataBase("id " + id + " não está cadastrado no sistema");
        }

        this.taskRepository.deleteById(id);
    }
}
