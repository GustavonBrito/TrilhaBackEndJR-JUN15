package com.codigocerto.desafiobackend.dto.mapper;

import com.codigocerto.desafiobackend.dto.response.UserAuthResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;

public class UserAuthMapper {
    public static UserAuthResponse transformEntityToResponse(UserEntity userEntity){
        return UserAuthResponse.builder()
                .id(userEntity.getId())
                .build();
    }
}
