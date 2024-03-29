package com.technicaltest.cuenta.controller;

import com.technicaltest.cuenta.business.CuentaService;
import com.technicaltest.cuenta.model.dto.CuentaResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
@Slf4j
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CuentaResponse> createdCuenta(@RequestBody Cuenta request) {
        return cuentaService.insertCuenta(request);
    }

    @GetMapping("/{numeroCuenta}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cuenta> getCuenta(@PathVariable Long numeroCuenta) {
        return cuentaService.getCuenta(numeroCuenta);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cuenta> putCuenta(@RequestBody Cuenta request) {
        return cuentaService.putCuenta(request);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCuenta(@PathVariable Long numeroCuenta) {
        cuentaService.deleteCuenta(numeroCuenta);
    }
}
