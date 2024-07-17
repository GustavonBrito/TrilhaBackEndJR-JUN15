package com.codigoCerto.desafioBackEnd.service;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    TaskResponse createTask(TaskRequest tasksRequest);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    TaskResponse updateTaskById(Long id);
    void deleteTaskById(Long id);

}
