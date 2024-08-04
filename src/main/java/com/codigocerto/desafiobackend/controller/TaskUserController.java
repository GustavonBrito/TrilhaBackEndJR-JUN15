package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.TaskUserRequest;
import com.codigocerto.desafiobackend.dto.response.TaskUserResponse;
import com.codigocerto.desafiobackend.service.impl.TaskUserService;
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

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("task_user")
public class TaskUserController {

    private final TaskUserService taskUserService;

    @Operation(summary = "Make relation of task and user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Save task user relation",
                                    description = "Save task user relation",
                                    value = " "
                            )
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
    public ResponseEntity<TaskUserResponse> saveTaskUserRelation(@Valid @RequestBody TaskUserRequest taskUserRequest){
        return ResponseEntity.ok().body(this.taskUserService.saveTaskUser(taskUserRequest));
    }
}

