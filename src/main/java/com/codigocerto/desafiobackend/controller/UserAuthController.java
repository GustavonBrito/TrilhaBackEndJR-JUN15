package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.UserAuthRequest;
import com.codigocerto.desafiobackend.dto.response.UserAuthResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import com.codigocerto.desafiobackend.infra.security.TokenGenerator;
import com.codigocerto.desafiobackend.service.impl.UserAuthService;
import com.codigocerto.desafiobackend.utils.ApiResponsesExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/auth")
public class UserAuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenGenerator tokenGenerator;

    private final UserAuthService userService;

    public UserAuthController(AuthenticationManager authenticationManager, TokenGenerator tokenGenerator, UserAuthService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.userService = userService;
    }

    @Operation(summary = "User Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Login a user",
                                    description = "Login a user",
                                    value = ApiResponsesExample.LOGIN_USER
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
    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> userLogin(@RequestBody @Valid UserAuthRequest userAuthRequest){
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthRequest.email(), userAuthRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var token = this.tokenGenerator.generateToken((UserEntity) auth.getPrincipal());
        UserAuthResponse userAuthResponse = this.userService.getUserLongByEmail(userAuthRequest.email());
        return ResponseEntity.ok(new UserAuthResponse(userAuthResponse.id(), token));
    }
}
