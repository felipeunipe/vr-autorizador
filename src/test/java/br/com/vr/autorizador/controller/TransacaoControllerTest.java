package br.com.vr.autorizador.controller;

import br.com.vr.autorizador.controller.request.TransacaoRequest;
import br.com.vr.autorizador.controller.request.TransacaoRequestStub;
import br.com.vr.autorizador.domain.exception.SaldoInsuficienteException;
import br.com.vr.autorizador.domain.service.transacao.TransacaoService;
import br.com.vr.autorizador.infrastructure.RestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    private static MockMvc mockMvc;

    @Mock
    private TransacaoService transacaoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TransacaoController(transacaoService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void transacionarSucesso() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()));

    }

    @Test
    void transacionarReturn422QuandoSaldoInsuficiente() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();

        doThrow(new SaldoInsuficienteException("SALDO_INSUFICIENTE"))
                .when(transacaoService).transacionar(any(TransacaoRequest.class));

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andExpect(jsonPath("$").value("SALDO_INSUFICIENTE"));

    }

    @Test
    void transacionarReturn400QuandoNumeroCartaoInvalido() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();
        transacaoRequestStub.setNumeroCartao("123");

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("Invalid request content."));

    }

    @Test
    void transacionarReturn400QuandoSenhaCartaoVazia() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();
        transacaoRequestStub.setSenhaCartao("");

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("Invalid request content."));

    }

    @Test
    void transacionarReturn400QuandoValorNulo() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();
        transacaoRequestStub.setValor(null);

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("Invalid request content."));

    }

    @Test
    void transacionarReturn400QuandoTransacaoDuplicada() throws Exception {

        var transacaoRequestStub = TransacaoRequestStub.criar();
        doThrow(OptimisticLockingFailureException.class).when(transacaoService).transacionar(any(TransacaoRequest.class));

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").value("TRANSACAO_DUPLICADA"));

    }

}
