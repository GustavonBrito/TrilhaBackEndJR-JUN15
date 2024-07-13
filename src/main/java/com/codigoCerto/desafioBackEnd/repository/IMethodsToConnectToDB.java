package com.codigoCerto.desafioBackEnd.repository;

import com.codigoCerto.desafioBackEnd.entity.UserEntity;

import java.util.List;

public interface IMethodsToConnectToDB<G> {

    G save(G g);
    List<G> findAll();
    G findById(Long id);
    G findByEmail (String email);
    G updateById (Long id, UserEntity userEntity);
    Boolean deleteById(Long id);
}
