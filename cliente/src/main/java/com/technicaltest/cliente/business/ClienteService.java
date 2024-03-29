package com.technicaltest.cliente.business;

import com.technicaltest.cliente.model.entity.Cliente;
import reactor.core.publisher.Mono;

public interface ClienteService {

    Mono<Cliente> getCliente(Long clientID);
    Mono<Cliente> getClienteByIdentification(Long identification);
    void insertCliente(Cliente cliente);

    void putCliente(Cliente cliente);
    void deleteCliente(Long clientId);
}
