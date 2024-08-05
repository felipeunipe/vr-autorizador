package br.com.vr.autorizador.domain.service.cartao;

import br.com.vr.autorizador.controller.request.CriaCartaoRequest;
import br.com.vr.autorizador.controller.response.CriaCartaoResponse;
import br.com.vr.autorizador.domain.entity.Cartao;
import br.com.vr.autorizador.domain.exception.CartaoExistenteException;
import br.com.vr.autorizador.domain.exception.CartaoInexistenteException;
import br.com.vr.autorizador.domain.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CriaCartaoResponse criarCartao(CriaCartaoRequest criaCartaoRequest) {

        cartaoRepository.findByNumeroCartao(criaCartaoRequest.getNumeroCartao())
                .ifPresent(cartao -> {
                    throw new CartaoExistenteException();
                });

        var cartao = salvarCartao(criaCartaoRequest);
        return CriaCartaoResponse.builder()
                .numeroCartao(cartao.getNumeroCartao())
                .senha(cartao.getSenha())
                .build();

    }

    private Cartao salvarCartao(CriaCartaoRequest criaCartaoRequest) {

        var cartao = Cartao.builder()
                .numeroCartao(criaCartaoRequest.getNumeroCartao())
                .senha(criaCartaoRequest.getSenha())
                .saldo(new BigDecimal("500"))
                .build();

        return cartaoRepository.save(cartao);
    }

    public BigDecimal consultarSaldoCartao(String numeroCartao) {

        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .map(Cartao::getSaldo)
                .orElseThrow(() -> new CartaoInexistenteException());

    }

}
