package br.com.vr.autorizador.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriaCartaoRequest {

    @Pattern(regexp = "^\\d{16}")
    private String numeroCartao;
    @NotEmpty(message = "testeeeee")
    private String senha;

}
