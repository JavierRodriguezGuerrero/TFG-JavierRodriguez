/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.security;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.entities.Pago;
import com.renthub.RentHub.models.entities.Vehiculo;
import com.renthub.RentHub.models.repository.AlquilerRepository;
import com.renthub.RentHub.models.repository.PagoRepository;
import com.renthub.RentHub.models.repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author javie
 */
@Component
public class AlquilerScheduler {

    @Autowired private AlquilerRepository alqRepo;
    @Autowired private PagoRepository pagoRepo;
    @Autowired private VehiculoRepository vehRepo;

    /**  
     * Se ejecuta cada día a medianoche (UTC).  
     * Ajusta la zona si lo necesitas.  
     */
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void procesarRenovaciones() {
        // Definimos inicio y fin del día “hoy”
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay   = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusNanos(1).toInstant();
        Date start = Date.from(startOfDay);
        Date end   = Date.from(endOfDay);

        List<Alquiler> due = alqRepo.findByFechaFinBetween(start, end);
        for (Alquiler al : due) {
            Vehiculo v = al.getVehiculo();
            if (al.isRenovacionAutomatica()) {
                // 1) Crear pago
                Pago p = new Pago();
                p.setAlquiler(al);
                p.setFechaPago(new Date());
                p.setCantidad(v.getPrecioMes());
                // No se guarda tarjeta/cvv en renovación; podrías guardarlo en Alquiler si lo necesitas
                p.setTarjeta("XXXX-XXXX-XXXX-XXXX");
                p.setCvv(0);
                p.setFechaTarjeta("MM/AA");
                pagoRepo.save(p);

                // 2) Extender fechas (+1 mes)
                Date oldFin = al.getFechaFin();
                Instant newStart = oldFin.toInstant();
                Instant newEnd   = newStart.plus(30, ChronoUnit.DAYS);
                al.setFechaInicio(Date.from(newStart));
                al.setFechaFin(Date.from(newEnd));
                alqRepo.save(al);
            } else {
                // Borrar alquiler y dejar vehículo disponible
                alqRepo.delete(al);
                v.setDisponible(true);
                vehRepo.save(v);
            }
        }
    }
}
