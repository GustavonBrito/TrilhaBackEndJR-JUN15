package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskUserRequest;
import com.codigoCerto.desafioBackEnd.service.impl.TaskUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("task_user")
public class TaskUserController {

    private final TaskUserService taskUserService;

    @Operation(summary = "Make relation of task and user" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Save task user relation",
                            description = "Save task user relation",
                            value = "{\n" +
                                    "    \"id\": 1,\n" +
                                    "    \"taskId\": 1,\n" +
                                    "    \"userId\": 1\n" +
                                    "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping
    public ResponseEntity<?> saveTaskUserRelation(@Valid @RequestBody TaskUserRequest taskUserRequest){
        return ResponseEntity.ok().body(this.taskUserService.saveTaskUser(taskUserRequest));
    }
}

