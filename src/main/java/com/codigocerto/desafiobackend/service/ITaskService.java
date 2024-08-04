package com.codigocerto.desafiobackend.service;

import com.codigocerto.desafiobackend.dto.request.TaskRequest;
import com.codigocerto.desafiobackend.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    TaskResponse createTask(TaskRequest tasksRequest);
    List<TaskResponse> getAllTasks(Integer page);
    TaskResponse getTaskById(Long id);
    TaskResponse updateTaskById(Long id, TaskRequest taskRequest);
    void deleteTaskById(Long id);
}
