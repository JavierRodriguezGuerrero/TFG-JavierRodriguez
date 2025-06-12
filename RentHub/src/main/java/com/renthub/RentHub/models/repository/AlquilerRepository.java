/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.renthub.RentHub.models.repository;

import com.renthub.RentHub.models.entities.Alquiler;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author javie
 */
public interface AlquilerRepository extends CrudRepository<Alquiler, Long>{
    
    List<Alquiler> findByFechaFinBetween(Date start, Date end);
    
}
