package com.technicaltest.cuenta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFlux
public class WebFluxWebConfig implements WebFluxConfigurer {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer){
        configurer.defaultCodecs().maxInMemorySize(20 * 1024 * 1024);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT","POST","GET")
                .maxAge(3600);
    }
}
