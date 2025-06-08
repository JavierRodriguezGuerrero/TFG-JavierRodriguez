/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.entities.Vehiculo;
import com.renthub.RentHub.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javie
 */
@RestController
@RequestMapping("/test-email")
public class TestEmailController {
  @Autowired private EmailService emailService;
  @GetMapping
  public ResponseEntity<Void> sendTest() {
    emailService.sendRentalConfirmation(
      new User("javier.rodriguez.guerrero.alu@iesjulioverne.es", "Prueba"), 
      new Vehiculo(), 
      new Alquiler(/* rellenar mínimos campos */)
    );
    return ResponseEntity.ok().build();
  }
}
