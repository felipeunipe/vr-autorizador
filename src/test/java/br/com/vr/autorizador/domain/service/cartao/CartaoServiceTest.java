package br.com.vr.autorizador.domain.service.cartao;

import br.com.vr.autorizador.controller.request.CriaCartaoRequestStub;
import br.com.vr.autorizador.domain.entity.Cartao;
import br.com.vr.autorizador.domain.entity.CartaoStub;
import br.com.vr.autorizador.domain.exception.CartaoExistenteException;
import br.com.vr.autorizador.domain.exception.CartaoInexistenteException;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

    @InjectMocks
    private CartaoService cartaoService;
    @Mock
    private CartaoRepository cartaoRepository;
    @Captor
    private ArgumentCaptor<Cartao> cartaoArgumentCaptor;

    @Test
    void criarCartaoRetornaExcessaoQuandoCartaoExiste() {

        doReturn(Optional.of(CartaoStub.criarCartaoDefault()))
                .when(cartaoRepository).findByNumeroCartao(anyString());

        var criarCartaoStub = CriaCartaoRequestStub.criarCartaoDefault();

        assertThrows(CartaoExistenteException.class, () -> cartaoService.criarCartao(criarCartaoStub));

        verify(cartaoRepository, never()).save(any(Cartao.class));

    }

    @Test
    void criarCartaoSucessoQuandoCartaoNaoExiste() {

        doReturn(Optional.empty())
                .when(cartaoRepository).findByNumeroCartao(anyString());

        var criarCartaoRequestStub = CriaCartaoRequestStub.criarCartaoDefault();

        var cartaoStub = Cartao.builder()
                .id("1")
                .numeroCartao(criarCartaoRequestStub.getNumeroCartao())
                .senha(criarCartaoRequestStub.getSenha())
                .saldo(new BigDecimal("500"))
                .build();

        doReturn(cartaoStub).when(cartaoRepository).save(any(Cartao.class));

        var result = assertDoesNotThrow(() -> cartaoService.criarCartao(criarCartaoRequestStub));

        verify(cartaoRepository).save(cartaoArgumentCaptor.capture());

        assertEquals(criarCartaoRequestStub.getNumeroCartao(), cartaoArgumentCaptor.getValue().getNumeroCartao());
        assertEquals(criarCartaoRequestStub.getSenha(), cartaoArgumentCaptor.getValue().getSenha());
        assertTrue(cartaoArgumentCaptor.getValue().getSaldo().compareTo(new BigDecimal("500")) == 0);

        assertEquals(cartaoStub.getNumeroCartao(), result.getNumeroCartao());
        assertEquals(cartaoStub.getSenha(), result.getSenha());

    }

    @Test
    void consultarSaldoCartaoRetornaExcessaoQuandoCartaoNaoExiste() {

        doReturn(Optional.empty()).when(cartaoRepository).findByNumeroCartao(anyString());
        assertThrows(CartaoInexistenteException.class, () -> cartaoService.consultarSaldoCartao("6549873025634501"));

    }

    @Test
    void consultarSaldoCartaoSucessoQuandoCartaoExiste() {

        doReturn(Optional.of(CartaoStub.criarCartaoDefault())).when(cartaoRepository).findByNumeroCartao(anyString());
        var saldo = cartaoService.consultarSaldoCartao("6549873025634501");
        assertTrue(saldo.compareTo(new BigDecimal("500")) == 0);

    }

}
