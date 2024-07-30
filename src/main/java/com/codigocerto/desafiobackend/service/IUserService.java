package com.codigocerto.desafiobackend.service;

import com.codigocerto.desafiobackend.dto.request.UserEditProfileRequest;
import com.codigocerto.desafiobackend.dto.request.UserSignUpRequest;
import com.codigocerto.desafiobackend.dto.response.UserEditProfileResponse;
import com.codigocerto.desafiobackend.dto.response.UserSignUpResponse;

import java.util.List;

public interface IUserService {
    UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest);
    List<UserSignUpResponse> getAllUsers();
    UserSignUpResponse getUserById(Long id);
    UserEditProfileResponse updateUserById(Long id, UserEditProfileRequest userEditProfileRequest);
    void deleteUserById(Long id);
}
