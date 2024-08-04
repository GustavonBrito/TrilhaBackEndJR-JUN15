package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.TaskRequest;
import com.codigocerto.desafiobackend.dto.response.TaskResponse;
import com.codigocerto.desafiobackend.service.impl.TaskServiceImpl;
import com.codigocerto.desafiobackend.utils.ApiResponsesExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Get all tasks registered in the database",
                                    description = "Get all tasks registered in the database",
                                    value = ApiResponsesExample.TASK_LIST
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = " ")
                    )
            )
    })
    @GetMapping("/getAllTasks/{page}")
    public ResponseEntity<List<TaskResponse>> getAllTasks(@PathVariable Integer page){
        List<TaskResponse> allTasks = this.taskService.getAllTasks(page);
        return ResponseEntity.ok().body(allTasks);
    }

    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Get task by id registered in database",
                                    description = "Get task by id registered in database",
                                    value = ApiResponsesExample.UNIQUE_TASK
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = " ")
                    )
            )
    })
    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.taskService.getTaskById(id));
    }

    @Operation(summary = "Task creation in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Create a task in database",
                                    description = "Create a task in database",
                                    value = ApiResponsesExample.UNIQUE_TASK
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = " ")
                    )
            )
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask (@Valid @RequestBody TaskRequest requestTask){
        TaskResponse taskCreated = this.taskService.createTask(requestTask);
        return ResponseEntity.created(URI.create("post/task")).body(taskCreated);
    }

    @Operation(summary = "Update Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Update a task in database",
                                    description = "Update a task in database",
                                    value = ApiResponsesExample.UNIQUE_TASK
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = " ")
                    )
            )
    })
    @PutMapping("{id}")
    public ResponseEntity<TaskResponse> updateTask (@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok().body(this.taskService.updateTaskById(id, taskRequest));
    }

    @Operation(summary = "Delete Task")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Delete a task in database",
                                    description = "Delete a task in database",
                                    value = " "
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "[]")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = " ")
                    )
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
}
