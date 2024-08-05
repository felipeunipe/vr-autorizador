package br.com.vr.autorizador.domain.exception;

import br.com.vr.autorizador.domain.exception.dto.Erro;
import org.springframework.http.HttpStatus;

public class SenhaInvalidaException extends HttpException {

    public SenhaInvalidaException(String codigoErro) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, new Erro(codigoErro));
    }

}
