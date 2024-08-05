package br.com.vr.autorizador.domain.exception;

import br.com.vr.autorizador.domain.exception.dto.Erro;
import org.springframework.http.HttpStatus;

public class CartaoInexistenteException extends HttpException {

    public CartaoInexistenteException() {
        super(HttpStatus.NOT_FOUND);
    }

    public CartaoInexistenteException(HttpStatus httpStatus, String codigoErro) {
        super(httpStatus, new Erro(codigoErro));
    }
}
