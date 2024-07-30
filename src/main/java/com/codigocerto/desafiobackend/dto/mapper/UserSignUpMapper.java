package com.codigocerto.desafiobackend.dto.mapper;

import com.codigocerto.desafiobackend.dto.request.UserSignUpRequest;
import com.codigocerto.desafiobackend.dto.response.UserSignUpResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserSignUpMapper {
    public static UserEntity transformRequestToEntity(UserSignUpRequest userSignUpRequest){
      return UserEntity.builder()
              .name(userSignUpRequest.name())
              .email(userSignUpRequest.email())
              .password(userSignUpRequest.password())
              .confirmedPassword(userSignUpRequest.confirmedPassword())
              .build();
    }

    public static UserSignUpResponse transformEntityToResponse(UserEntity userEntity){
        return UserSignUpResponse.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }
}
