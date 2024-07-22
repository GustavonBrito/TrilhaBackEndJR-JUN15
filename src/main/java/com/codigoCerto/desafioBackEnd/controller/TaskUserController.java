package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.TaskUserRequest;
import com.codigoCerto.desafioBackEnd.service.impl.TaskUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("task_user")
public class TaskUserController {

    private final TaskUserService taskUserService;

    @PostMapping
    public ResponseEntity<?> saveTaskUserRelation(@Valid @RequestBody TaskUserRequest taskUserRequest){
        return ResponseEntity.ok().body(this.taskUserService.saveTaskUser(taskUserRequest));
    }
}

