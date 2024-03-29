package com.technicaltest.cuenta.model.repository;

import com.technicaltest.cuenta.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findAllByClientId(Long clientId);
}
