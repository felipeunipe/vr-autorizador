package br.com.vr.autorizador.controller.request;

public class CriaCartaoRequestStub {

    public static CriaCartaoRequest criarCartaoDefault() {

        return CriaCartaoRequest.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();

    }

}
