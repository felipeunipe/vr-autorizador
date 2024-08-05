package br.com.vr.autorizador.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriaCartaoResponse {

    private String numeroCartao;
    private String senha;

}
