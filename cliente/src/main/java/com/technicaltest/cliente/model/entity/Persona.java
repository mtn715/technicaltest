package com.technicaltest.cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "identificacion", unique = true , nullable = false )
    private Long identificacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

}
