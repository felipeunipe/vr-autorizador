package br.com.vr.autorizador.domain.service.transacao.validadores;

import br.com.vr.autorizador.domain.dto.DadosValidacaoTransacaoDTOStub;
import br.com.vr.autorizador.domain.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidadorTransacaoSaldoTest {

    @InjectMocks
    private ValidadorTransacaoSaldo validadorTransacaoSaldo;

    @Test
    void validarRetornaExcessaoQuandoSaldoInsuficiente() {

        var dadosValidacaoStub = DadosValidacaoTransacaoDTOStub.criarDadosComCartao(new BigDecimal("5"));
        var erro = assertThrows(SaldoInsuficienteException.class, () -> validadorTransacaoSaldo.validar(dadosValidacaoStub));
        assertEquals("SALDO_INSUFICIENTE", erro.getErro().getCodigo());

    }

    @Test
    void validarSucessoQuandoTiverSaldo() {

        var dadosValidacaoStub = DadosValidacaoTransacaoDTOStub.criarDadosComCartao();
        assertDoesNotThrow(() -> validadorTransacaoSaldo.validar(dadosValidacaoStub));

    }

}
