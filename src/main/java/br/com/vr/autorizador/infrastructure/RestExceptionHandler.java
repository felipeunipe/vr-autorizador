package br.com.vr.autorizador.infrastructure;


import br.com.vr.autorizador.domain.exception.HttpException;
import br.com.vr.autorizador.domain.exception.dto.Erro;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(HttpException ex, WebRequest request) {

        var codigoErro = Optional.ofNullable(ex.getErro())
                .map(Erro::getCodigo)
                .orElseGet(() -> null);

        return handleExceptionInternal(ex, codigoErro, new HttpHeaders(), ex.getHttpStatus(), request);

    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(OptimisticLockingFailureException ex, WebRequest request) {

        return handleExceptionInternal(ex, "TRANSACAO_DUPLICADA", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

}
