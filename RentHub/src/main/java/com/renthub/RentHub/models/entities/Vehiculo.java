/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.models.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvehiculo;
    
    private String marca;
    
    private String modelo;
    
    private String matricula; //hacer unico
    
    private String tipo;
    
    private Double precioMes;
    
    private String km;
    
    private String fuel;
    
    private String category;
    
    private String transmission;
    
    private String descripcion;
    
    //añadir mas campos para pagina y relaciones...
    
    @ElementCollection
    @CollectionTable(
      name = "vehiculo_imagenes",
      joinColumns = @JoinColumn(name = "vehiculo_id")
    )
    @Column(name = "url")
    private List<String> imagenes = new ArrayList<>();
    

    public Vehiculo(String marca, String modelo, String matricula, String tipo, Double precioMes, String km, String descripcion) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.precioMes = precioMes;
        this.km = km;
        this.descripcion = descripcion;
    }

    public Vehiculo(String marca, String modelo, String matricula, String tipo, Double precioMes, String km, String fuel, String category, String transmission, String descripcion) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.precioMes = precioMes;
        this.km = km;
        this.fuel = fuel;
        this.category = category;
        this.transmission = transmission;
        this.descripcion = descripcion;
    }

    
    
    
    public Vehiculo() {
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioMes() {
        return precioMes;
    }

    public void setPrecioMes(Double precioMes) {
        this.precioMes = precioMes;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    
    
    
}
