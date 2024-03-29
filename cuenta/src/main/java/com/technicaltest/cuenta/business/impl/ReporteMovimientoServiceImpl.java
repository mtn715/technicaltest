package com.technicaltest.cuenta.business.impl;

import com.technicaltest.cuenta.business.ReporteService;
import com.technicaltest.cuenta.client.ClientRest;
import com.technicaltest.cuenta.model.dto.ReporteMovimientoResponse;
import com.technicaltest.cuenta.model.entity.Cuenta;
import com.technicaltest.cuenta.model.repository.CuentaRepository;
import com.technicaltest.cuenta.model.repository.MovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Qualifier("movimiento")
@Slf4j
public class ReporteMovimientoServiceImpl implements ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClientRest webClient;

    @Override
    public Mono<List<ReporteMovimientoResponse>> getReporteMovimiento(LocalDateTime fechaInicial,
                                                                      LocalDateTime fechaFinal, Long clientId) {
        return webClient.getCliente(clientId)
                .map( cliente -> {
                    List<Cuenta> cuentas = cuentaRepository.findAllByClientId(clientId);
                    List<Long> cuentasId = new ArrayList<>();
                    cuentas.forEach(cuenta -> cuentasId.add(cuenta.getNumeroCuenta()));
                    List<ReporteMovimientoResponse>  reporteMovimientoResponsesList= new ArrayList<>();
                    reporteMovimientoResponsesList = convertToObjectList(movimientoRepository
                            .obtenerMovimiento(fechaInicial,fechaFinal,cuentasId));

                    reporteMovimientoResponsesList.forEach(
                            reporteMovimientoResponse -> {
                                reporteMovimientoResponse.setCliente(cliente.getNombre());
                                List<Cuenta> cuenta = cuentas.stream()
                                        .filter( cuenta1 ->
                                                cuenta1.getNumeroCuenta() == reporteMovimientoResponse.getNumeroCuenta())
                                        .collect(Collectors.toList());
                                reporteMovimientoResponse.setTipoCuenta(cuenta.get(0).getTipo());
                                reporteMovimientoResponse.setSaldoInicial(cuenta.get(0).getSaldoInicial() - reporteMovimientoResponse.getMovimiento());
                                reporteMovimientoResponse.setEstado(cuenta.get(0).getEstado());
                                reporteMovimientoResponse.setSaldoDisponible(cuenta.get(0).getSaldoInicial());
                            });
                    return reporteMovimientoResponsesList;
                });
    }

    public static List<ReporteMovimientoResponse> convertToObjectList(List<Object[]> results) {
        return results.stream()
                .map(ReporteMovimientoServiceImpl::convertToObject)
                .toList();
    }

    private static ReporteMovimientoResponse convertToObject(Object[] result) {

        LocalDateTime fechaMovimiento = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = fechaMovimiento.format(formato);
        Long numeroCuenta = (Long) result[1];
        Double movimiento = (Double) result[2];

        return new ReporteMovimientoResponse(fecha, null, numeroCuenta, null,  null, null, movimiento,null);
    }

}
