package br.com.vr.autorizador.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class CartaoStub {

    public static Cartao criarCartaoDefault() {
        return criarCartaoDefault(null);
    }

    public static Cartao criarCartaoDefault(BigDecimal saldo) {

        var saldoCartao = Optional.ofNullable(saldo)
                .orElseGet(() -> new BigDecimal("500"));

        return Cartao.builder()
                .id("1")
                .numeroCartao("6549873025634501")
                .senha("1234")
                .saldo(saldoCartao)
                .dhCriacao(LocalDateTime.now())
                .dhAtualizacao(LocalDateTime.now())
                .build();

    }

}
