package br.com.vr.autorizador.controller.request;

import java.math.BigDecimal;

public class TransacaoRequestStub {

    public static TransacaoRequest criar() {

        return TransacaoRequest.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.TEN)
                .build();

    }

}
