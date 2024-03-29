package com.technicaltest.cuenta.client.impl;

import com.technicaltest.cuenta.client.ClientRest;
import com.technicaltest.cuenta.model.dto.Cliente;
import com.technicaltest.cuenta.exception.RegistroNoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRestImpl implements ClientRest {
    private final WebClient webClient;

    @Value("${application.endpoints.url.get_client}")
    private String urlGetClient;
    @Override
    public Mono<Cliente> getCliente(Long request) {
        log.info("Buscando cliente");
        return this.retrieveWebClient(request)
                .bodyToMono(Cliente.class)
                .onErrorResume(this::fallbackRenewal);
    }

    private WebClient.ResponseSpec retrieveWebClient(Long clientId) {
        return webClient
                .get()
                .uri(urlGetClient, clientId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
    }
    public Mono<Cliente> fallbackRenewal(Throwable error) {
         if (error instanceof WebClientResponseException) {
            return responseException((WebClientResponseException) error);
        }
        return Mono.error(error);
    }

    private Mono<Cliente> responseException(WebClientResponseException error) {
        if(error.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            log.info("Cliente no encontrado");
            throw new RegistroNoEncontradoException("Ocurrió un error de registro no encontrado");
        }
        return Mono.error(error);
    }
}
