package br.com.vr.autorizador.domain.service.transacao;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTO;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import br.com.vr.autorizador.domain.service.transacao.validadores.ValidadorTransacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final CartaoRepository cartaoRepository;
    private final List<ValidadorTransacao> validadoresTransacao;

    public void transacionar(TransacaoRequest transacaoRequest) {

        var cartaoOpt = cartaoRepository.findByNumeroCartao(transacaoRequest.getNumeroCartao());

        var dadosTransacao = DadosValidacaoTransacaoDTO.builder()
                .cartao(cartaoOpt)
                .transacaoRequest(transacaoRequest)
                .build();

        validadoresTransacao.forEach(validadorTransacao -> validadorTransacao.validar(dadosTransacao));

        cartaoOpt.ifPresent(cartao -> {
            var novoSaldo = cartao.getSaldo().subtract(transacaoRequest.getValor());
            cartao.setSaldo(novoSaldo);
            cartaoRepository.save(cartao);
        });

    }

}
