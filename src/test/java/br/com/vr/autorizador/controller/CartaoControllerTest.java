package br.com.vr.autorizador.controller;

import br.com.vr.autorizador.controller.request.CriaCartaoRequest;
import br.com.vr.autorizador.controller.request.CriaCartaoRequestStub;
import br.com.vr.autorizador.controller.request.CriaCartaoResponseStub;
import br.com.vr.autorizador.domain.exception.CartaoExistenteException;
import br.com.vr.autorizador.domain.service.cartao.CartaoService;
import br.com.vr.autorizador.infrastructure.RestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CartaoControllerTest {

    private static MockMvc mockMvc;

    @Mock
    private CartaoService cartaoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CartaoController(cartaoService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void criarCartaoReturnStatusCode422WhenCartaoExiste() throws Exception {

        var criaCartaoStub = CriaCartaoRequestStub.criarCartaoDefault();

        doThrow(new CartaoExistenteException())
                .when(cartaoService).criarCartao(any(CriaCartaoRequest.class));

        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criaCartaoStub))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andExpect(content().json(objectMapper.writeValueAsString(criaCartaoStub)));

    }

    @Test
    void criarCartaoSucesso() throws Exception {

        var criaCartaoStub = CriaCartaoRequestStub.criarCartaoDefault();
        var criaCartaoResponseStub = CriaCartaoResponseStub.criar();

        doReturn(criaCartaoResponseStub)
                .when(cartaoService).criarCartao(any(CriaCartaoRequest.class));

        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criaCartaoStub))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().json(objectMapper.writeValueAsString(criaCartaoResponseStub)));

    }

    @Test
    void criarCartaoExceptionWhenNumeroCartaoInvalido() throws Exception {

        var criaCartaoStub = CriaCartaoRequestStub.criarCartaoDefault();
        criaCartaoStub.setNumeroCartao("123");
        var criaCartaoResponseStub = CriaCartaoResponseStub.criar();

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criaCartaoStub))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("Invalid request content."));

    }

    @Test
    void consultarSaldoCartaoSucesso() throws Exception {

        doReturn(new BigDecimal("450.55")).when(cartaoService).consultarSaldoCartao(eq("6549873025634501"));

        mockMvc.perform(get("/cartoes/6549873025634501")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").value("450.55"));

    }

    @Test
    void consultarSaldoCartaoRetorna404QuandoNumeroCartaoInvalido() throws Exception {

        mockMvc.perform(get("/cartoes/12315")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));

    }

}
