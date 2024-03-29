package com.technicaltest.cuenta.business;

import com.technicaltest.cuenta.model.dto.ReporteMovimientoResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public interface ReporteService {
    Mono<List<ReporteMovimientoResponse>> getReporteMovimiento(LocalDateTime fechaInicial,
                                                               LocalDateTime fechaFinal,
                                                               Long clientId);
}
