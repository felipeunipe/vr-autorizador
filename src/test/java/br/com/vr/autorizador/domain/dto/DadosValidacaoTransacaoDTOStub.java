package br.com.vr.autorizador.domain.dto;

import br.com.vr.autorizador.controller.request.TransacaoRequestStub;
import br.com.vr.autorizador.domain.entity.CartaoStub;

import java.math.BigDecimal;
import java.util.Optional;

public class DadosValidacaoTransacaoDTOStub {

    public static DadosValidacaoTransacaoDTO criarDadosSemCartao() {

        return DadosValidacaoTransacaoDTO.builder()
                .transacaoRequest(TransacaoRequestStub.criar())
                .cartao(Optional.empty())
                .build();

    }

    public static DadosValidacaoTransacaoDTO criarDadosComCartao() {
        return criarDadosComCartao(null);
    }

    public static DadosValidacaoTransacaoDTO criarDadosComCartao(BigDecimal saldo) {

        return DadosValidacaoTransacaoDTO.builder()
                .transacaoRequest(TransacaoRequestStub.criar())
                .cartao(Optional.of(CartaoStub.criarCartaoDefault(saldo)))
                .build();

    }

}
