package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.UserEditProfileRequest;
import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserEditProfileResponse;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.service.impl.UserServiceImpl;
import com.codigoCerto.desafioBackEnd.utils.ApiResponsesExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Get all Users" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Update a user in database",
                            description = "Update a user in database",
                            value = ApiResponsesExample.USER_LIST)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping
    public ResponseEntity<List<UserSignUpResponse>> getAllUsers(){
        List<UserSignUpResponse> allUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Get User by id" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get user by id in database",
                            description = "Get user by id in database",
                            value = ApiResponsesExample.UNIQUE_USER)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userServiceImpl.getUserById(id));
    }

    @Operation(summary = "Register User" ,responses = {
            @ApiResponse(responseCode = "201", content = @Content(examples = {
                    @ExampleObject(name = "Create a user",
                            description = "Create a user",
                            value = ApiResponsesExample.UNIQUE_USER)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/signUp")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        return ResponseEntity.created(URI.create("/user")).body(userServiceImpl.createUser(userSignUpRequest));
    }

    @Operation(summary = "Update all informations about user" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Update a user",
                            description = "Update a user",
                            value = ApiResponsesExample.UNIQUE_USER)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PutMapping("{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody @Valid UserEditProfileRequest userEditProfileRequest){
        UserEditProfileResponse userEditProfileResponse = userServiceImpl.updateUserById(id, userEditProfileRequest);
        return ResponseEntity.ok().body(userEditProfileResponse);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
