package com.codigocerto.desafiobackend.repository;

import java.util.List;

public interface IMethodsToConnectToDB<G> {
    G save(G g);
    List<G> findAll(Integer page);
    G findById(Long id);
    G updateById (Long id, G g);
    void deleteById(Long id);
}
