package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTO;
import br.com.vr.autorizador.domain.exception.SaldoInsuficienteException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorTransacaoSaldo implements ValidadorTransacao, Ordered {

    @Override
    public void validar(DadosValidacaoTransacaoDTO dadosValidacaoTransacaoDTO) {

        var cartaoOpt = dadosValidacaoTransacaoDTO.getCartao();
        var transacaoRequest = dadosValidacaoTransacaoDTO.getTransacaoRequest();
        cartaoOpt.filter(cartao -> cartao.getSaldo().compareTo(transacaoRequest.getValor()) >= 0)
                .orElseThrow(() -> new SaldoInsuficienteException("SALDO_INSUFICIENTE"));


    }

    @Override
    public int getOrder() {
        return 3;
    }

}
