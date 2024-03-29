package com.technicaltest.cliente.model.repository;

import com.technicaltest.cliente.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente getClienteByIdentificacion(Long identificacion);
    Cliente findClienteByClientId(Long clientId);
}
