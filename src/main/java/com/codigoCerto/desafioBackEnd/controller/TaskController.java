package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.service.impl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok().body(this.taskService.getAllTasks());
    }

    @Operation(summary = "Get task by id")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.taskService.getTaskById(id));
    }

    @Operation(summary = "Create Task")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @PostMapping
    public ResponseEntity<?> createTask (@Valid @RequestBody TaskRequest requestTask){
        TaskResponse taskCreated = this.taskService.createTask(requestTask);
        return ResponseEntity.created(URI.create("post/task")).body(taskCreated);
    }

    @Operation(summary = "Update Task")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @PutMapping("{id}")
    public ResponseEntity<?> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok().body(this.taskService.updateTaskById(id, taskRequest));
    }

    @Operation(summary = "Delete Task")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
