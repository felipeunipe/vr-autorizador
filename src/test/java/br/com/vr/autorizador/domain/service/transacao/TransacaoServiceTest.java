package br.com.vr.autorizador.domain.service.transacao;

import br.com.vr.autorizador.controller.request.TransacaoRequestStub;
import br.com.vr.autorizador.domain.entity.Cartao;
import br.com.vr.autorizador.domain.entity.CartaoStub;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import br.com.vr.autorizador.domain.service.transacao.validadores.ValidadorTransacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.AssertionErrors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;
    @Mock
    private CartaoRepository cartaoRepository;
    @Mock
    private List<ValidadorTransacao> validadoresTransacao;
    @Captor
    private ArgumentCaptor<Cartao> cartaoArgumentCaptor;

    @Test
    void transacionarSucesso() {

        doReturn(Optional.of(CartaoStub.criarCartaoDefault()))
                .when(cartaoRepository).findByNumeroCartao(anyString());

        var transacaoRequestStub = TransacaoRequestStub.criar();

        transacaoService.transacionar(transacaoRequestStub);

        verify(cartaoRepository).save(cartaoArgumentCaptor.capture());

        assertTrue(cartaoArgumentCaptor.getValue().getSaldo().compareTo(new BigDecimal("490")) == 0);

    }

}
