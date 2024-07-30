package com.codigocerto.desafiobackend.service.impl;

import com.codigocerto.desafiobackend.dto.mapper.UserAuthMapper;
import com.codigocerto.desafiobackend.dto.response.UserAuthResponse;
import com.codigocerto.desafiobackend.entity.UserEntity;
import com.codigocerto.desafiobackend.repository.impl.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userRepository.findByEmailToGetCredentials(email).getUsername();
        return userRepository.findByEmailToGetCredentials(email);
    }

    public UserAuthResponse getUserLongByEmail(String email){
        UserEntity userLong = this.userRepository.findByEmail(email);
        return UserAuthMapper.transformEntityToResponse(userLong);
    }
}
