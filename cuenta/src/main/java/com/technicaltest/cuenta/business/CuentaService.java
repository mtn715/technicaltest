package com.technicaltest.cuenta.business;

import com.technicaltest.cuenta.model.dto.CuentaResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import reactor.core.publisher.Mono;

public interface CuentaService {

    Mono<CuentaResponse> insertCuenta(Cuenta cuenta);
    Mono<Cuenta> getCuenta(Long numeroCuenta);

    Mono<Cuenta> putCuenta(Cuenta cuenta);

    void deleteCuenta(Long numeroCuenta);
}
