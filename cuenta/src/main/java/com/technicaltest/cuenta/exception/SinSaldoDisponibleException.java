package com.technicaltest.cuenta.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SinSaldoDisponibleException extends RuntimeException {
    public SinSaldoDisponibleException(String mensaje) {
        super(mensaje);
    }

    public SinSaldoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
