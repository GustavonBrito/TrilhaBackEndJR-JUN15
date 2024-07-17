package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@Valid @PathVariable Long id){
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createTask (@Valid @RequestBody TaskRequest requestTask){
        TaskResponse taskCreated = this.taskService.createTask(requestTask);
        return ResponseEntity.created(URI.create("post/task")).body(taskCreated);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest requestBody){
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        return null;
    }
}
