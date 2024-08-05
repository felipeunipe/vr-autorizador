package br.com.vr.autorizador.domain.exception;

import br.com.vr.autorizador.domain.exception.dto.Erro;
import org.springframework.http.HttpStatus;

public class SaldoInsuficienteException extends HttpException {

    public SaldoInsuficienteException(String codigoErro) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, new Erro(codigoErro));
    }

}
