package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTO;
import br.com.vr.autorizador.domain.entity.Cartao;
import br.com.vr.autorizador.domain.exception.CartaoInexistenteException;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidadorTransacaoCartaoInexistente implements ValidadorTransacao, Ordered {

    private final CartaoRepository cartaoRepository;

    @Override
    public void validar(DadosValidacaoTransacaoDTO dadosValidacaoTransacaoDTO) {
        var cartaoOpt = dadosValidacaoTransacaoDTO.getCartao();
        cartaoOpt.orElseThrow(() -> new CartaoInexistenteException(HttpStatus.UNPROCESSABLE_ENTITY, "CARTAO_INEXISTENTE"));
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
