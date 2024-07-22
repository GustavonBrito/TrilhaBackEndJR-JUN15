package com.codigoCerto.desafioBackEnd.service.impl;

import com.codigoCerto.desafioBackEnd.dto.mapper.UserAuthMapper;
import com.codigoCerto.desafioBackEnd.dto.response.UserAuthResponse;
import com.codigoCerto.desafioBackEnd.entity.UserEntity;
import com.codigoCerto.desafioBackEnd.repository.impl.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

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
