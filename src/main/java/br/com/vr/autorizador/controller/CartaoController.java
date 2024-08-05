package br.com.vr.autorizador.controller;

import br.com.vr.autorizador.controller.request.CriaCartaoRequest;
import br.com.vr.autorizador.controller.response.CriaCartaoResponse;
import br.com.vr.autorizador.domain.exception.CartaoExistenteException;
import br.com.vr.autorizador.domain.service.cartao.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CriaCartaoResponse> criarCartao(@RequestBody @Validated CriaCartaoRequest criaCartaoRequest) {

        try {

            var criaCartaoResponse = cartaoService.criarCartao(criaCartaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(criaCartaoResponse);

        } catch (CartaoExistenteException ex) {

            var criaCartaoResponse = CriaCartaoResponse.builder()
                    .numeroCartao(criaCartaoRequest.getNumeroCartao())
                    .senha(criaCartaoRequest.getSenha())
                    .build();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(criaCartaoResponse);

        }

    }

    @GetMapping("/{numeroCartao:^\\d{16}$}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal consultarSaldoCartao(@PathVariable("numeroCartao") String numeroCartao) {
        return cartaoService.consultarSaldoCartao(numeroCartao);
    }

}
