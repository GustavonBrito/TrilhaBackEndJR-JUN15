package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskRequest;
import com.codigoCerto.desafioBackEnd.dto.response.TaskResponse;
import com.codigoCerto.desafioBackEnd.service.impl.TaskServiceImpl;
import com.codigoCerto.desafioBackEnd.utils.ApiResponsesExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @Operation(summary = "Get all tasks" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get all tasks registered in database",
                            description = "Get all tasks registered in database",
                            value = ApiResponsesExample.TASK_LIST
                    )
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok().body(this.taskService.getAllTasks());
    }

    @Operation(summary = "Get task by id" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get task by id registered in database",
                            description = "Get task by id registered in database",
                            value = ApiResponsesExample.UNIQUE_TASK
                    )
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.taskService.getTaskById(id));
    }

    @Operation(summary = "Task creation in database" ,responses = {
            @ApiResponse(responseCode = "201", content = @Content(examples = {
                    @ExampleObject(name = "Create a task in database",
                            description = "Create a task in database",
                            value = ApiResponsesExample.UNIQUE_TASK
                                   )
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping
    public ResponseEntity<?> createTask (@Valid @RequestBody TaskRequest requestTask){
        TaskResponse taskCreated = this.taskService.createTask(requestTask);
        return ResponseEntity.created(URI.create("post/task")).body(taskCreated);
    }

    @Operation(summary = "Update Task" ,responses = {
            @ApiResponse(responseCode = "201", content = @Content(examples = {
                    @ExampleObject(name = "Update a task in database",
                            description = "Update a task in database",
                            value = ApiResponsesExample.UNIQUE_TASK)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PutMapping("{id}")
    public ResponseEntity<?> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok().body(this.taskService.updateTaskById(id, taskRequest));
    }

    @Operation(summary = "Delete Task" ,responses = {
            @ApiResponse(responseCode = "404", content = @Content(examples = {
                    @ExampleObject(name = "Delete a task in database",
                            description = "Delete a task in database",
                            value = ApiResponsesExample.UNIQUE_TASK)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
