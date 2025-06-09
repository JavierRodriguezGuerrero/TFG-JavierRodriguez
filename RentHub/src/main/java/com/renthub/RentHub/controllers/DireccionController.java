/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.models.entities.Direccion;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.repository.DireccionRepository;
import com.renthub.RentHub.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/users/me/direccion")
@CrossOrigin(origins = "http://localhost:4200")
public class DireccionController {

    @Autowired private UserRepository userRepo;
    @Autowired private DireccionRepository dirRepo;

    /** Obtiene la dirección del usuario autenticado (o 404 si no existe) */
    @GetMapping
    public ResponseEntity<Direccion> getDireccion(@AuthenticationPrincipal UserDetails ud) {
        User u = userRepo.findByUsername(ud.getUsername())
                         .orElseThrow();
        Direccion d = u.getDireccion();
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    /** Crea (o sobrescribe) la dirección del usuario autenticado */
    @PostMapping
    public ResponseEntity<Direccion> createDireccion(
            @AuthenticationPrincipal UserDetails ud,
            @RequestBody Direccion nueva
    ) {
        User u = userRepo.findByUsername(ud.getUsername()).orElseThrow();
        // Persistir la nueva dirección
        Direccion saved = dirRepo.save(nueva);
        u.setDireccion(saved);
        userRepo.save(u);
        return ResponseEntity.ok(saved);
    }

    /** Actualiza la dirección existente */
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(
            @AuthenticationPrincipal UserDetails ud,
            @PathVariable Long id,
            @RequestBody Direccion cambios
    ) {
        User u = userRepo.findByUsername(ud.getUsername()).orElseThrow();
        Direccion actual = u.getDireccion();
        if (actual == null || !actual.getIddireccion().equals(id)) {
            return ResponseEntity.notFound().build();
        }
        // Aplicar cambios
        actual.setCalle(cambios.getCalle());
        actual.setCiudad(cambios.getCiudad());
        actual.setCodigoPostal(cambios.getCodigoPostal());
        actual.setPais(cambios.getPais());
        Direccion saved = dirRepo.save(actual);
        return ResponseEntity.ok(saved);
    }
}
