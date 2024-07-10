package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<UserSignUpResponse>> getAllUsers(){
        List<UserSignUpResponse> allUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        return ResponseEntity.created(URI.create("/user")).body(userServiceImpl.createUser(userSignUpRequest));
    };
//    @PutMapping
//
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
