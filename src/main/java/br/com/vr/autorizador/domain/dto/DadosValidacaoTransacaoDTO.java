package br.com.vr.autorizador.domain.dto;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.domain.entity.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosValidacaoTransacaoDTO {

    private TransacaoRequest transacaoRequest;
    private Optional<Cartao> cartao;

}
