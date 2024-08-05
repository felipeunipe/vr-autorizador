package br.com.vr.autorizador.domain.repository;

import br.com.vr.autorizador.domain.entity.Cartao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, Long> {

    Optional<Cartao> findByNumeroCartao(String numeroCartao);
    Optional<Cartao> findByNumeroCartaoAndSenha(String numeroCartao, String senha);

}
