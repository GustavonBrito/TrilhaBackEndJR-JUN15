package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.UserEditProfileRequest;
import com.codigocerto.desafiobackend.dto.request.UserSignUpRequest;
import com.codigocerto.desafiobackend.dto.response.UserEditProfileResponse;
import com.codigocerto.desafiobackend.dto.response.UserSignUpResponse;
import com.codigocerto.desafiobackend.service.impl.UserServiceImpl;
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/getAllUsers/{page}")
    public ResponseEntity<List<UserSignUpResponse>> getAllUsers(@PathVariable Integer page){
        List<UserSignUpResponse> allUsers = userServiceImpl.getAllUsers(page);
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Get User by id" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get user by id in database",
                            description = "Get user by id in database",
                            value = ApiResponsesExample.UNIQUE_USER)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("{id}")
    public ResponseEntity<UserSignUpResponse> getUserById(@PathVariable Long id){
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
    public ResponseEntity<UserSignUpResponse> createUser(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        return ResponseEntity.created(URI.create("/user")).body(userServiceImpl.createUser(userSignUpRequest));
    }

    @Operation(summary = "Update all informations about user" ,responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Update a user",
                            description = "Update a user",
                            value = ApiResponsesExample.UNIQUE_USER)
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PutMapping("{id}")
    public ResponseEntity<UserEditProfileResponse> updateUserById(@PathVariable Long id, @RequestBody @Valid UserEditProfileRequest userEditProfileRequest){
        UserEditProfileResponse userEditProfileResponse = userServiceImpl.updateUserById(id, userEditProfileRequest);
        return ResponseEntity.ok().body(userEditProfileResponse);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseEntity.BodyBuilder> deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
