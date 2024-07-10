package com.codigoCerto.desafioBackEnd.service;

import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;

import java.util.List;

public interface IUserService {
    UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest);
    List<UserSignUpResponse> getAllUsers();
    UserSignUpResponse getUserById(Long id);
    UserSignUpResponse updateUserById(Long id, UserSignUpRequest userSignUpRequest);
    void deleteUserById(Long id);
}
