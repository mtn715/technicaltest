package com.technicaltest.cuenta.model.repository;

import com.technicaltest.cuenta.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT fechaMovimiento, numeroCuenta, SUM(valor) " +
            "FROM Movimiento " +
            "WHERE fechaMovimiento BETWEEN ?1 AND ?2 AND numeroCuenta IN ?3 " +
            "GROUP BY fechaMovimiento, numeroCuenta")
    List<Object[]> obtenerMovimiento(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<Long> clienId);
}
