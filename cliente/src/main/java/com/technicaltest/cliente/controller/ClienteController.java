package com.technicaltest.cliente.controller;

import com.technicaltest.cliente.business.ClienteService;
import com.technicaltest.cliente.model.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cliente> getCliente(@PathVariable Long clientId){
        return clienteService.getCliente(clientId);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cliente> getClienteByIdentification(
            @RequestParam(value = "identification" , required = true, defaultValue = "") Long identification){
        return clienteService.getClienteByIdentification(identification);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createdCliente(@RequestBody Cliente request) {
        clienteService.insertCliente(request);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void putCliente(@RequestBody Cliente request){
        clienteService.putCliente(request);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCliente(@PathVariable Long clientId){
        clienteService.deleteCliente(clientId);
    }
}
