package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.TaskRequest;
import com.codigocerto.desafiobackend.dto.response.TaskResponse;
import com.codigocerto.desafiobackend.service.impl.TaskServiceImpl;
import com.codigocerto.desafiobackend.utils.ApiResponsesExample;
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
import java.util.List;

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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/getAllTasks/{page}")
    public ResponseEntity<List<TaskResponse>> getAllTasks(@PathVariable Integer page){
        List<TaskResponse> allTasks = this.taskService.getAllTasks(page);
        return ResponseEntity.ok().body(allTasks);
    }

    @Operation(summary = "Get task by id" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get task by id registered in database",
                            description = "Get task by id registered in database",
                            value = ApiResponsesExample.UNIQUE_TASK
                    )
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask (@Valid @RequestBody TaskRequest requestTask){
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PutMapping("{id}")
    public ResponseEntity<TaskResponse> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok().body(this.taskService.updateTaskById(id, taskRequest));
    }

    @Operation(summary = "Delete Task" ,responses = {
            @ApiResponse(responseCode = "404", content = @Content(examples = {
                    @ExampleObject(name = "Delete a task in database",
                            description = "Delete a task in database",
                            value = ApiResponsesExample.UNIQUE_TASK)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "200", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
}
