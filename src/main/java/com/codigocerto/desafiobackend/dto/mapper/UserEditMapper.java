package com.codigocerto.desafiobackend.dto.mapper;

import com.codigocerto.desafiobackend.dto.request.UserEditProfileRequest;
import com.codigocerto.desafiobackend.dto.response.UserEditProfileResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {
    public static UserEntity transformRequestToEntity(UserEditProfileRequest userEditProfileRequest){
        return UserEntity.builder()
                .name(userEditProfileRequest.name())
                .email(userEditProfileRequest.email())
                .build();
    }

    public static UserEditProfileResponse transformEntityToResponse(UserEntity userEntity){
        return UserEditProfileResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }
}
