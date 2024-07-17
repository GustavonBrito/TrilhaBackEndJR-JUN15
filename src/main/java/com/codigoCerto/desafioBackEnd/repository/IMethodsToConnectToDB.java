package com.codigoCerto.desafioBackEnd.repository;

import java.util.List;

public interface IMethodsToConnectToDB<G> {
    G save(G g);
    List<G> findAll();
    G findById(Long id);
    G updateById (Long id, G g);
    void deleteById(Long id);
}
