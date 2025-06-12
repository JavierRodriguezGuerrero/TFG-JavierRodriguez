/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.models.entities.Vehiculo;
import com.renthub.RentHub.services.VehiculoService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author javie
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehiculos")
public class VehiculoController {
    
    @Autowired
    private VehiculoService vehiculoService;
    
    @GetMapping
    public List<Vehiculo> getAll() {
        return vehiculoService.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Vehiculo getById(@PathVariable Long id) {
      return vehiculoService.findById(id);
    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Vehiculo> createVehiculo(
        @RequestParam String marca,
        @RequestParam String modelo,
        @RequestParam String matricula,
        @RequestParam(required = false) String tipo,
        @RequestParam Double precioMes,
        @RequestParam(required = false) String km,
        @RequestParam(required = false) String fuel,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String transmission,
        @RequestParam(required = false) String descripcion,
        @RequestParam("imagenes") MultipartFile[] imagenes
    ) throws IOException {
        Vehiculo v = vehiculoService.saveWithImages(
          marca, modelo, matricula, tipo,
          precioMes, km, fuel, category,
          transmission, descripcion, imagenes
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(v);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
    
}
