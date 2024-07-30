package com.codigocerto.desafiobackend.controller;

import com.codigocerto.desafiobackend.dto.request.UserAuthRequest;
import com.codigocerto.desafiobackend.dto.response.UserAuthResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import com.codigocerto.desafiobackend.infra.security.TokenGenerator;
import com.codigocerto.desafiobackend.service.impl.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
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
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> userLogin(@RequestBody @Valid UserAuthRequest userAuthRequest){
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthRequest.email(), userAuthRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var token = this.tokenGenerator.generateToken((UserEntity) auth.getPrincipal());
        UserAuthResponse userAuthResponse = this.userService.getUserLongByEmail(userAuthRequest.email());
        return ResponseEntity.ok(new UserAuthResponse(userAuthResponse.id(), token));
    }
}
