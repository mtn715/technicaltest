package com.technicaltest.cliente.business.impl;

import com.technicaltest.cliente.business.ClienteService;
import com.technicaltest.cliente.client.exception.RegistroDuplicadoException;
import com.technicaltest.cliente.client.exception.RegistroNoEncontradoException;
import com.technicaltest.cliente.model.repository.ClienteRepository;
import com.technicaltest.cliente.model.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Qualifier("cliente")
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Mono<Cliente> getCliente(Long clientId) {
        try {
            log.info("Buscando cliente");
            return Mono.just(clienteRepository.findClienteByClientId(clientId));
        } catch (Exception e){
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

    @Override
    public Mono<Cliente> getClienteByIdentification(Long identification) {
        try {
            log.info("Buscando cliente");
            return Mono.just(clienteRepository.getClienteByIdentificacion(identification));
        } catch (Exception e){
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

    @Override
    public void insertCliente(Cliente cliente) {
        Cliente Checked = clienteRepository.getClienteByIdentificacion(cliente.getIdentificacion());
        if(!Objects.nonNull(Checked)){
        cliente.setEstado(true);
            clienteRepository.save(cliente);
        log.info("Creando cliente");
        } else {
            throw new RegistroDuplicadoException("Ocurrió un error de registro duplicado");
        }
    }

    @Override
    public void putCliente(Cliente cliente) {
        if(clienteRepository.existsById(cliente.getClientId())){
            clienteRepository.save(cliente);
            log.info("Cliente actualizado");
        } else {
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

    @Override
    public void deleteCliente(Long clientId) {
        if(clienteRepository.existsById(clientId)){
            clienteRepository.deleteById(clientId);
            log.info("Cliente eliminado");
        } else {
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
    }

}
