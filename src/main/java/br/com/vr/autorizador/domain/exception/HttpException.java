package br.com.vr.autorizador.domain.exception;

import br.com.vr.autorizador.domain.exception.dto.Erro;
import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {

    private HttpStatus httpStatus;
    private Erro erro;

    public HttpException(String message, Throwable cause, HttpStatus httpStatus, Erro erro) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.erro = erro;
    }

    public HttpException(Throwable cause, HttpStatus httpStatus, Erro erro) {
        super(cause);
        this.httpStatus = httpStatus;
        this.erro = erro;
    }

    public HttpException(HttpStatus httpStatus, Erro erro) {
        this.httpStatus = httpStatus;
        this.erro = erro;
    }

    public HttpException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Erro getErro() {
        return erro;
    }
}
