package com.technicaltest.cuenta.business.impl;

import com.technicaltest.cuenta.business.MovimientoService;
import com.technicaltest.cuenta.exception.SinSaldoDisponibleException;
import com.technicaltest.cuenta.model.dto.MovimientoResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import com.technicaltest.cuenta.model.entity.Movimiento;
import com.technicaltest.cuenta.exception.RegistroNoEncontradoException;
import com.technicaltest.cuenta.model.repository.CuentaRepository;
import com.technicaltest.cuenta.model.repository.MovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;


@Service
@Qualifier("movimiento")
@Slf4j
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public MovimientoResponse insertMovimiento(Movimiento movimiento) {

        // Convertir Optional<Clase> a Clase
        Cuenta cuenta =  cuentaRepository.findById(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new IllegalStateException("No existe la cuenta"));
        if(Objects.nonNull(cuenta)){
            Double saldo = cuenta.getSaldoInicial() + movimiento.getValor();
            movimiento.setEstado(true);
            if (movimiento.getTipoMovimiento().equalsIgnoreCase("Retiro")
                    && movimiento.getValor() < 0){
                validarSaldo(cuenta, movimiento, saldo);
            } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("Deposito")
                    && movimiento.getValor() > 0){
                validarSaldo(cuenta, movimiento, saldo);
            } else {
                log.info("Tipo de Movimiento no válido");
                throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
            }
        } else {
            log.info("Cuenta no encontrada");
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
        log.info("Movimiento realizado");
        return MovimientoResponse.builder()
                .numeroCuenta(movimiento.getNumeroCuenta())
                .tipo(cuenta.getTipo())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(movimiento.getEstado())
                .movimiento(movimiento.getTipoMovimiento() + " de " + Math.abs(movimiento.getValor()))
                .build();
    }

    @Override
    public Mono<MovimientoResponse> getMovimiento(Long movimientoId) {

        Movimiento movimiento = movimientoRepository.findById(movimientoId)
                .orElseThrow(() -> new IllegalStateException("No existe la cuenta"));;

        Cuenta cuenta =  cuentaRepository.findById(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new IllegalStateException("No existe la cuenta"));

        return Mono.justOrEmpty(MovimientoResponse.builder()
                .numeroCuenta(movimiento.getNumeroCuenta())
                .tipo(cuenta.getTipo())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(movimiento.getEstado())
                .movimiento(movimiento.getTipoMovimiento() + " de " + Math.abs(movimiento.getValor()))
                .build());
    }

    @Override
    public void deleteMovimiento (Long movimientoId) {
        if(movimientoRepository.existsById(movimientoId)){
            Movimiento movimiento = movimientoRepository.findById(movimientoId).orElseThrow();
            Cuenta cuenta = cuentaRepository.findById(movimiento.getNumeroCuenta()).orElseThrow();
            Double saldo = cuenta.getSaldoInicial() + movimiento.getValor();
            validarSaldo(cuenta, movimiento, saldo);
            movimientoRepository.deleteById(movimientoId);
            log.info("Movimiento eliminado");
        } else {
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

    private Mono<Movimiento> validarSaldo(Cuenta cuenta, Movimiento movimiento, Double saldo){
        if (saldo > 0){
            movimiento.setSaldo(cuenta.getSaldoInicial());
            cuenta.setSaldoInicial(saldo);
            cuentaRepository.save(cuenta);
            return Mono.justOrEmpty( movimientoRepository.save(movimiento));
        } else {
            log.info("No cuenta con saldo disponible");
            throw new SinSaldoDisponibleException("No cuenta con saldo disponible");
        }
    }
}
