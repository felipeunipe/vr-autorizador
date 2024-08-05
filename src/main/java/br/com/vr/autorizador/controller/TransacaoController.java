package br.com.vr.autorizador.controller;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.domain.service.transacao.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> transactionar(@RequestBody @Validated TransacaoRequest transacaoRequest) {

        transacaoService.transacionar(transacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("OK");

    }


}
