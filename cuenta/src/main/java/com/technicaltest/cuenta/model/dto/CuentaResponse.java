package com.technicaltest.cuenta.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CuentaResponse {
    private Long numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private Boolean estado;
    private String cliente;
}
