package com.technicaltest.cuenta.client;

import com.technicaltest.cuenta.model.dto.Cliente;
import reactor.core.publisher.Mono;

public interface ClientRest {
    Mono<Cliente> getCliente(Long clientId) ;
}
