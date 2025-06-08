/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.entities.Pago;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.entities.Vehiculo;
import com.renthub.RentHub.models.repository.AlquilerRepository;
import com.renthub.RentHub.models.repository.PagoRepository;
import com.renthub.RentHub.models.repository.UserRepository;
import com.renthub.RentHub.models.repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javie
 */
@Service
@Transactional
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepo;
    @Autowired 
    private VehiculoRepository vehRepo;
    @Autowired 
    private PagoRepository pagoRepo;
   @Autowired 
   private UserRepository userRepo;
   @Autowired 
   private EmailService emailService;

    /**
     * Desactiva la renovación automática en el alquiler indicado.
     */
    public void cancelRenewal(Long idAlquiler) {
        Alquiler al = alquilerRepo.findById(idAlquiler)
            .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
        al.setRenovacionAutomatica(false);
        alquilerRepo.save(al);
    }
    
    @Transactional
    public void rentVehicle(
        Long vehiculoId,
        String cardNumber,
        String expiry,
        String cvv,
        String username
    ) {
      Vehiculo v = vehRepo.findById(vehiculoId)
        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
      if (!v.isDisponible()) {
        throw new RuntimeException("Vehículo no disponible");
      }
      v.setDisponible(false);
      vehRepo.save(v);

      User u = userRepo.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

      Alquiler al = new Alquiler();
      al.setVehiculo(v);
      al.setUser(u);
      al.setFechaInicio(Date.from(Instant.now()));
      al.setFechaFin(Date.from(Instant.now().plusSeconds(60L*60*24*30)));
      al.setRenovacionAutomatica(true);
      al = alquilerRepo.save(al);

      Pago p = new Pago();
      p.setAlquiler(al);
      p.setFechaPago(Date.from(Instant.now()));
      p.setCantidad(v.getPrecioMes());
      p.setTarjeta(cardNumber);
      p.setCvv(Integer.parseInt(cvv));
      p.setFechaTarjeta(expiry);
      pagoRepo.save(p);
      emailService.sendRentalConfirmation(u, v, al);
    }
    
}
