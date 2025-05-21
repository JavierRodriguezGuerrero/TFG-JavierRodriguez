/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.renthub.RentHub.models.repository;

import com.renthub.RentHub.models.entities.Vehiculo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author javie
 */
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long>{
    
}
