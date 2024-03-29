package com.technicaltest.cuenta.business.impl;

import com.technicaltest.cuenta.business.CuentaService;
import com.technicaltest.cuenta.client.ClientRest;
import com.technicaltest.cuenta.model.dto.CuentaResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import com.technicaltest.cuenta.exception.RegistroNoEncontradoException;
import com.technicaltest.cuenta.model.repository.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Qualifier("cuenta")
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClientRest webClient;

    @Override
    public Mono<CuentaResponse> insertCuenta(Cuenta cuenta) {
        return webClient.getCliente(cuenta.getClientId())
                .map( cliente -> {
                    cuenta.setEstado(true);
                    cuentaRepository.save(cuenta);
                    log.info("Cuenta creada");
                    return CuentaResponse.builder()
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .tipo(cuenta.getTipo())
                            .saldoInicial(cuenta.getSaldoInicial())
                            .estado(cuenta.getEstado())
                            .cliente(cliente.getNombre())
                            .build();
                });
    }

    @Override
    public Mono<Cuenta> getCuenta(Long numeroCuenta) {
        return Mono.justOrEmpty(cuentaRepository.findById(numeroCuenta));
    }

    @Override
    public Mono<Cuenta> putCuenta(Cuenta cuenta) {
        if (cuentaRepository.existsById(cuenta.getNumeroCuenta())) {
            return webClient.getCliente(cuenta.getClientId())
                    .map(cliente -> {
                        cuenta.setEstado(true);
                        return cuentaRepository.save(cuenta);
                    });
        } else {
            log.info("Cuenta no encontrada");
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

    @Override
    public void deleteCuenta(Long numeroCuenta) {
        if(cuentaRepository.existsById(numeroCuenta)){
            cuentaRepository.deleteById(numeroCuenta);
            log.info("Cuenta eliminada");
        } else {
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }
}
