package com.technicaltest.cuenta.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tmovimientos")
public class Movimiento {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id", unique = true , nullable = false )
    private Long movimientoId;
    @Column(name = "numero_cuenta", nullable = false )
    private Long numeroCuenta;
    @Column(name = "fecha_movimiento")
    private LocalDateTime fechaMovimiento = LocalDateTime.now();
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Column(name = "valor")
    private double valor;
    @Column(name = "saldo")
    private double saldo;
    @Column(name = "estado")
    private Boolean estado;
}
