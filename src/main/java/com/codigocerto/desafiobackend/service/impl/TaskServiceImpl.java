package com.codigocerto.desafiobackend.service.impl;

import com.codigocerto.desafiobackend.dto.mapper.TaskMapper;
import com.codigocerto.desafiobackend.dto.request.TaskRequest;
import com.codigocerto.desafiobackend.dto.response.TaskResponse;
import com.codigocerto.desafiobackend.entity.TaskEntity;
import com.codigocerto.desafiobackend.exception.IsIdStoredAtDataBase;
import com.codigocerto.desafiobackend.repository.impl.TaskRepository;
import com.codigocerto.desafiobackend.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    TaskRepository taskRepository;

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
