package com.codigoCerto.desafioBackEnd.dto.mapper;

import com.codigoCerto.desafioBackEnd.dto.request.UserEditProfileRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserEditProfileResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {
    public static UserEntity transformRequestToEntity(UserEditProfileRequest userEditProfileRequest){
        return UserEntity.builder()
                .name(userEditProfileRequest.name())
                .email(userEditProfileRequest.email())
                .build();
    };

    public static UserEditProfileResponse transformEntityToResponse(UserEntity userEntity){
        return UserEditProfileResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
