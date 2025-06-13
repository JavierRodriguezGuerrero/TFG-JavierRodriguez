/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "alquiler")
public class Alquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalquiler;
    
    
    private Date fechaInicio;
    
    private Date fechaFin;
    
    private boolean renovacionAutomatica;
    
    //relaciones...
    
    @ManyToOne(optional = true)
    @JoinColumn(
        name = "iduser",             
        referencedColumnName = "iduser",
        nullable = true              
    )
    @JsonIgnoreProperties("alquileres")
    private User user;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "idvehiculo",
        referencedColumnName = "idvehiculo",
        nullable = false         
    )
    private Vehiculo vehiculo;
    
    @OneToMany(
        mappedBy = "alquiler",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnoreProperties("alquiler")
    private List<Pago> pagos = new ArrayList<>();

    public Alquiler() {
    }
    
    

    public Alquiler(Date fechaInicio, Date fechaFin, boolean renovacionAutomatica,Vehiculo vehiculo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.renovacionAutomatica = renovacionAutomatica;
        this.vehiculo = vehiculo;
    }

    public Long getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(Long idalquiler) {
        this.idalquiler = idalquiler;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(boolean renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public void addPago(Pago pago) {
        pagos.add(pago);
        pago.setAlquiler(this);
    }

    public void removePago(Pago pago) {
        pagos.remove(pago);
        pago.setAlquiler(null);
    }
    
   
    
}
