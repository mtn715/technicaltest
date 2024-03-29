package com.technicaltest.cliente.client.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNoEncontradoException extends RuntimeException {
    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public RegistroNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
