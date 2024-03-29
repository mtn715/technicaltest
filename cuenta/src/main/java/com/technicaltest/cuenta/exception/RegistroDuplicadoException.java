package com.technicaltest.cuenta.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegistroDuplicadoException  extends RuntimeException {
    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
    }

    public RegistroDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
