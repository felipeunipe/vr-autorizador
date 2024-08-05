package br.com.vr.autorizador.domain.exception.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Erro {

    private final String codigo;

    public String getCodigo() {
        return codigo;
    }

}
