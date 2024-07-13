package com.codigoCerto.desafioBackEnd.service;

import com.codigoCerto.desafioBackEnd.dto.request.UserEditProfileRequest;
import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserEditProfileResponse;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;

import java.util.List;

public interface IUserService {
    UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest);
    List<UserSignUpResponse> getAllUsers();
    UserSignUpResponse getUserById(Long id);
    UserEditProfileResponse updateUserById(Long id, UserEditProfileRequest userEditProfileRequest);
    void deleteUserById(Long id);
}
