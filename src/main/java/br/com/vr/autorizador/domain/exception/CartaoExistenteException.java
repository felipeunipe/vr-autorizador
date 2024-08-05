package br.com.vr.autorizador.domain.exception;

import org.springframework.http.HttpStatus;

public class CartaoExistenteException extends HttpException {

    public CartaoExistenteException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
