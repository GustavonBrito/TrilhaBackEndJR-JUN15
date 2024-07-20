package com.codigoCerto.desafioBackEnd.dto.mapper;

import com.codigoCerto.desafioBackEnd.dto.response.UserAuthResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;

public class UserAuthMapper {
    public static UserAuthResponse transformEntityToResponse(UserEntity userEntity){
        return UserAuthResponse.builder()
                .id(userEntity.getId())
                .build();
    }
}
