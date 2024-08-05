package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTOStub;
import br.com.vr.autorizador.domain.exception.SenhaInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidadorTransacaoSenhaTest {

    @InjectMocks
    private ValidadorTransacaoSenha validadorTransacaoSenha;

    @Test
    void validarRetornaErroQuandoSenhaInvalida() {

        var dadosTransacao = DadosValidacaoTransacaoDTOStub.criarDadosComCartao();
        dadosTransacao.getTransacaoRequest().setSenhaCartao("4321");

        var erro = assertThrows(SenhaInvalidaException.class, () -> validadorTransacaoSenha.validar(dadosTransacao));

        assertEquals("SENHA_INVALIDA", erro.getErro().getCodigo());

    }

    @Test
    void validarSucessoQuandoSenhaValida() {

        var dadosTransacao = DadosValidacaoTransacaoDTOStub.criarDadosComCartao();

        assertDoesNotThrow(() -> validadorTransacaoSenha.validar(dadosTransacao));

    }


}
