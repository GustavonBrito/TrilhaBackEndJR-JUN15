package com.codigoCerto.desafioBackEnd.controller;

import com.codigoCerto.desafioBackEnd.dto.request.UserAuthRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserAuthResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.infra.security.TokenGenerator;
import com.codigoCerto.desafioBackEnd.service.impl.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserAuthService userService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserAuthRequest userAuthRequest){
        UserEntity entityToGenerateToken = new UserEntity();
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthRequest.email(), userAuthRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        entityToGenerateToken.setEmail(String.valueOf(auth.getPrincipal()));
        var token = tokenGenerator.generateToken(entityToGenerateToken);
        UserAuthResponse userAuthResponse = this.userService.getUserLongByEmail(userAuthRequest.email());
        return ResponseEntity.ok(new UserAuthResponse(userAuthResponse.id(), token));
    }
}
