package com.technicaltest.cuenta.controller;

import com.technicaltest.cuenta.business.ReporteService;
import com.technicaltest.cuenta.model.dto.ReporteMovimientoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@Slf4j
public class ReportesController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<List<ReporteMovimientoResponse>> getReporte(
            @RequestParam(value = "fechaInicial" , required = false, defaultValue = "") String fechaInicial,
            @RequestParam(value = "fechaFinal" , required = false, defaultValue = "") String fechaFinal,
            @RequestParam(value = "clientId" , required = false, defaultValue = "") Long clientId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFechaInicio = LocalDateTime.parse(fechaInicial + " 00:00", formatter);
        LocalDateTime dateFechaFin = LocalDateTime.parse(fechaFinal +  " 23:59", formatter);
        return reporteService.getReporteMovimiento(dateFechaInicio, dateFechaFin, clientId);
    }


}
