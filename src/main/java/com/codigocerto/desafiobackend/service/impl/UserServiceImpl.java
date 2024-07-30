package com.codigocerto.desafiobackend.service.impl;

import com.codigocerto.desafiobackend.dto.mapper.UserEditMapper;
import com.codigocerto.desafiobackend.dto.mapper.UserSignUpMapper;
import com.codigocerto.desafiobackend.dto.request.UserEditProfileRequest;
import com.codigocerto.desafiobackend.dto.request.UserSignUpRequest;
import com.codigocerto.desafiobackend.dto.response.UserEditProfileResponse;
import com.codigocerto.desafiobackend.dto.response.UserSignUpResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import com.codigocerto.desafiobackend.exception.EmailAlreadyExists;
import com.codigocerto.desafiobackend.exception.IsIdStoredAtDataBase;
import com.codigocerto.desafiobackend.exception.IsPasswordEquals;
import com.codigocerto.desafiobackend.repository.impl.UserRepository;
import com.codigocerto.desafiobackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest) {
        UserEntity userEmailAlreadyRegistered = userRepository.findByEmail(userSignUpRequest.email());
        if (userEmailAlreadyRegistered.getEmail() != null){
            throw new EmailAlreadyExists("Email já cadastrado no sistema");
        }
        if (!Objects.equals(userSignUpRequest.password(), userSignUpRequest.confirmedPassword())){
            throw new IsPasswordEquals("Senhas não iguais");
        }
        UserEntity userTransformedToEntity = UserSignUpMapper.transformRequestToEntity(userSignUpRequest);
        userTransformedToEntity.setPassword(new BCryptPasswordEncoder().encode(userTransformedToEntity.getPassword()));
        userTransformedToEntity.setConfirmedPassword(new BCryptPasswordEncoder().encode(userTransformedToEntity.getConfirmedPassword()));
        UserEntity savedUserInRepo = this.userRepository.save(userTransformedToEntity);
        return UserSignUpMapper.transformEntityToResponse(savedUserInRepo);
    }

    @Override
    public List<UserSignUpResponse> getAllUsers() {
        List<UserEntity> allUsersFoundByRepo = userRepository.findAll();
        return allUsersFoundByRepo.stream().map(UserSignUpMapper::transformEntityToResponse).toList();
    }

    @Override
    public UserSignUpResponse getUserById(Long id) {
        UserEntity userReturnedFromRepo = userRepository.findById(id);
        if (userReturnedFromRepo.getId() == null){
            throw new IsIdStoredAtDataBase("Id não está armazenado no sistema");
        }
        return UserSignUpMapper.transformEntityToResponse(userReturnedFromRepo);
    }

    @Override
    public UserEditProfileResponse updateUserById(Long id, UserEditProfileRequest userEditProfileRequest) {
        UserEntity userReturnedFromRepo = userRepository.findById(id);
        if (userReturnedFromRepo.getId() == null){
            throw new IsIdStoredAtDataBase("Id não está armazenado no sistema");
        }
        UserEntity userEntity = UserEditMapper.transformRequestToEntity(userEditProfileRequest);
        UserEntity userEntityUpdatedFromRepo = userRepository.updateById(id, userEntity);
        return UserEditMapper.transformEntityToResponse(userEntityUpdatedFromRepo);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userReturnedFromRepo = userRepository.findById(id);
        if (userReturnedFromRepo.getId() == null){
            throw new IsIdStoredAtDataBase("Id não está armazenado no sistema");
        }
        this.userRepository.deleteById(id);
    }
}
