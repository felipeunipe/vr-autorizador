package br.com.vr.autorizador.controller.request;

import br.com.vr.autorizador.controller.response.CriaCartaoResponse;

public class CriaCartaoResponseStub {

    public static CriaCartaoResponse criar() {

        return CriaCartaoResponse.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();

    }

}
