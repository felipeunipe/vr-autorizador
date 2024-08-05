package br.com.vr.autorizador.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {

    @Pattern(regexp = "^\\d{16}")
    private String numeroCartao;
    @NotEmpty
    private String senhaCartao;
    @NotNull
    private BigDecimal valor;

}
