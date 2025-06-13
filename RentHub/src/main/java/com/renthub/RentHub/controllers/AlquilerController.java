/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.services.AlquilerService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javie
 */
@RestController
@RequestMapping("/alquiler")
@CrossOrigin(origins = "http://localhost:4200")
public class AlquilerController {
    @Autowired
    private AlquilerService alquilerService;

    
    @PutMapping("/{id}/cancelRenewal")
    public ResponseEntity<Void> cancelRenewal(@PathVariable("id") Long idAlquiler) {
        alquilerService.cancelRenewal(idAlquiler);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<Void> rent(
        @RequestBody Map<String,Object> body,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
      
      Long vehiculoId = Long.valueOf(body.get("vehiculoId").toString());
      String cardNumber = body.get("cardNumber").toString();
      String expiry     = body.get("expiry").toString();
      String cvv        = body.get("cvv").toString();

      
      alquilerService.rentVehicle(vehiculoId, cardNumber, expiry, cvv, userDetails.getUsername());
      return ResponseEntity.ok().build();
    }
    
}
