package com.technicaltest.cuenta.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tcuentas")
public class Cuenta {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cuenta", unique = true , nullable = false )
    private Long numeroCuenta;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "saldo_inicial")
    private double saldoInicial;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "estado")
    private Boolean estado;
}
