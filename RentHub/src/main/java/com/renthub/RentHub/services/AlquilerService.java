/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.repository.AlquilerRepository;
import jakarta.transaction.Transactional;
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

    /**
     * Desactiva la renovación automática en el alquiler indicado.
     */
    public void cancelRenewal(Long idAlquiler) {
        Alquiler al = alquilerRepo.findById(idAlquiler)
            .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
        al.setRenovacionAutomatica(false);
        alquilerRepo.save(al);
    }
}
