package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.UserSignUpMapper;
import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.repository.impl.UserRepository;
import com.codigoCerto.desafioBackEnd.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest) {
        UserEntity userTransformedToEntity = UserSignUpMapper.transformRequestToEntity(userSignUpRequest);
        UserEntity savedUserInRepo = this.userRepository.save(userTransformedToEntity);
        return UserSignUpMapper.transformEntityToResponse(savedUserInRepo);
    }

    @Override
    public List<UserSignUpResponse> getAllUsers() {
        List<UserEntity> allUsersFindedByRepo = userRepository.findAll();
        return allUsersFindedByRepo.stream().map(UserSignUpMapper::transformEntityToResponse).toList();
    }

    @Override
    public UserSignUpResponse getUserById(Long id) {
        UserEntity userReturnedFromRepo = userRepository.findById(id);
        return UserSignUpMapper.transformEntityToResponse(userReturnedFromRepo);
    }

    @Override
    public UserSignUpResponse updateUserById(Long id, UserSignUpRequest userSignUpRequest) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }
}
