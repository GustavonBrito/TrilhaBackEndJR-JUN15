package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.UserEditProfileRequest;
import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserEditProfileResponse;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Operation(summary = "User Login")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @GetMapping
    public ResponseEntity<List<UserSignUpResponse>> getAllUsers(){
        List<UserSignUpResponse> allUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Get User by id")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userServiceImpl.getUserById(id));
    }

    @Operation(summary = "Register a user")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/signUp")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        return ResponseEntity.created(URI.create("/user")).body(userServiceImpl.createUser(userSignUpRequest));
    }

    @Operation(summary = "Update ALL informations about user")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @PutMapping("{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody @Valid UserEditProfileRequest userEditProfileRequest){
        UserEditProfileResponse userEditProfileResponse = userServiceImpl.updateUserById(id, userEditProfileRequest);
        return ResponseEntity.ok().body(userEditProfileResponse);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthourized")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
