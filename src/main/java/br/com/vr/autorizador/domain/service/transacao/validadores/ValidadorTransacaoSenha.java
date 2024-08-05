package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTO;
import br.com.vr.autorizador.domain.entity.Cartao;
import br.com.vr.autorizador.domain.exception.SenhaInvalidaException;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidadorTransacaoSenha implements ValidadorTransacao, Ordered {

    private final CartaoRepository cartaoRepository;

    @Override
    public void validar(DadosValidacaoTransacaoDTO dadosValidacaoTransacaoDTO) {

        var cartaoOpt = dadosValidacaoTransacaoDTO.getCartao();
        var transacaoRequest = dadosValidacaoTransacaoDTO.getTransacaoRequest();
        cartaoOpt.filter(cartao -> cartao.getSenha().equals(transacaoRequest.getSenhaCartao()))
                .orElseThrow(() -> new SenhaInvalidaException("SENHA_INVALIDA"));

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
