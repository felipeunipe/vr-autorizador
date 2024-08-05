package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTOStub;
import br.com.vr.autorizador.domain.exception.CartaoInexistenteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidadorTransacaoCartaoInexistenteTest {

    @InjectMocks
    private ValidadorTransacaoCartaoInexistente validadorTransacaoCartaoInexistente;

    @Test
    void validarDoReturnExceptionWhenCartaoEmpty() {

        var dadosValidacaoTransacao = DadosValidacaoTransacaoDTOStub.criarDadosSemCartao();
        var erro = assertThrows(CartaoInexistenteException.class,
                () -> validadorTransacaoCartaoInexistente.validar(dadosValidacaoTransacao));

        assertEquals("CARTAO_INEXISTENTE", erro.getErro().getCodigo());


    }

    @Test
    void validarSuccessWhenCartaoNotEmpty() {

        var dadosValidacaoTransacao = DadosValidacaoTransacaoDTOStub.criarDadosComCartao();
        assertDoesNotThrow(() -> validadorTransacaoCartaoInexistente.validar(dadosValidacaoTransacao));

    }

}
