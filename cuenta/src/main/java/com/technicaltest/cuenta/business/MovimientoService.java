package com.technicaltest.cuenta.business;

import com.technicaltest.cuenta.model.dto.MovimientoResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import com.technicaltest.cuenta.model.entity.Movimiento;
import reactor.core.publisher.Mono;

public interface MovimientoService {

    MovimientoResponse insertMovimiento(Movimiento movimiento);
    Mono<MovimientoResponse> getMovimiento(Long movimientoId);
    void deleteMovimiento(Long movimientoId);
}
