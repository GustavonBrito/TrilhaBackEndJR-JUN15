package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.UserEditMapper;
import com.codigoCerto.desafioBackEnd.dto.mapper.UserSignUpMapper;
import com.codigoCerto.desafioBackEnd.dto.request.UserEditProfileRequest;
import com.codigoCerto.desafioBackEnd.dto.request.UserSignUpRequest;
import com.codigoCerto.desafioBackEnd.dto.response.UserEditProfileResponse;
import com.codigoCerto.desafioBackEnd.dto.response.UserSignUpResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.exception.EmailAlreadyExists;
import com.codigoCerto.desafioBackEnd.exception.IsIdStoredAtDataBase;
import com.codigoCerto.desafioBackEnd.exception.IsPasswordEquals;
import com.codigoCerto.desafioBackEnd.repository.impl.UserRepository;
import com.codigoCerto.desafioBackEnd.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
