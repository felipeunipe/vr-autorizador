package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTO;

public interface ValidadorTransacao {

    void validar(DadosValidacaoTransacaoDTO dadosValidacaoTransacaoDTO);

}
