/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.services.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    /**
     * PUT /alquiler/{id}/cancelRenewal
     * Cancela la renovación automática para ese alquiler.
     */
    @PutMapping("/{id}/cancelRenewal")
    public ResponseEntity<Void> cancelRenewal(@PathVariable("id") Long idAlquiler) {
        alquilerService.cancelRenewal(idAlquiler);
        return ResponseEntity.noContent().build();
    }
}
