/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "pago")
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpago;
    
    private Date fechaPago;
    
    private Double cantidad;
    
    private String tarjeta;
    
    private int cvv;
    
    private String fechaTarjeta;
    
    
    //relaciones...
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
        name = "idalquiler",
        referencedColumnName = "idalquiler",
        nullable = false
    )
    @JsonIgnoreProperties("pagos")
    private Alquiler alquiler;

    public Pago(Date fechaPago, Double cantidad, String tarjeta, int cvv, String fechaTarjeta, Alquiler alquiler) {
        this.fechaPago = fechaPago;
        this.cantidad = cantidad;
        this.tarjeta = tarjeta;
        this.cvv = cvv;
        this.fechaTarjeta = fechaTarjeta;
        this.alquiler = alquiler;
    }

    public Pago() {
    }

    public Pago(Long idpago, Date fechaPago, Double cantidad, String tarjeta, int cvv, String fechaTarjeta, Alquiler alquiler) {
        this.idpago = idpago;
        this.fechaPago = fechaPago;
        this.cantidad = cantidad;
        this.tarjeta = tarjeta;
        this.cvv = cvv;
        this.fechaTarjeta = fechaTarjeta;
        this.alquiler = alquiler;
    }

    public Pago(Date fechaPago, Double cantidad, String tarjeta, int cvv, String fechaTarjeta) {
        this.fechaPago = fechaPago;
        this.cantidad = cantidad;
        this.tarjeta = tarjeta;
        this.cvv = cvv;
        this.fechaTarjeta = fechaTarjeta;
    }

    public Long getIdpago() {
        return idpago;
    }

    public void setIdpago(Long idpago) {
        this.idpago = idpago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getFechaTarjeta() {
        return fechaTarjeta;
    }

    public void setFechaTarjeta(String fechaTarjeta) {
        this.fechaTarjeta = fechaTarjeta;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
    
    
}
