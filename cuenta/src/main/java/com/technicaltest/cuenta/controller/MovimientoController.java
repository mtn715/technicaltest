package com.technicaltest.cuenta.controller;

import com.technicaltest.cuenta.business.MovimientoService;
import com.technicaltest.cuenta.model.dto.MovimientoResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import com.technicaltest.cuenta.model.entity.Movimiento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movimientos")
@Slf4j
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoResponse createdMovimiento(@RequestBody Movimiento request) {
        return movimientoService.insertMovimiento(request);
    }

    @GetMapping("/{movimientoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovimientoResponse> getMovimiento(@PathVariable Long movimientoId) {
        return movimientoService.getMovimiento(movimientoId);
    }

    @DeleteMapping("/{movimientoId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCuenta(@PathVariable Long movimientoId) {
        movimientoService.deleteMovimiento(movimientoId);
    }

}
