package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok().body(this.taskService.getAllTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTask (@Valid @RequestBody TaskRequest requestTask){
        TaskResponse taskCreated = this.taskService.createTask(requestTask);
        return ResponseEntity.created(URI.create("post/task")).body(taskCreated);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok().body(this.taskService.updateTaskById(id, taskRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
