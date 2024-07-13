package com.codigoCerto.desafioBackEnd.dto.mapper;

import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
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
    };

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
