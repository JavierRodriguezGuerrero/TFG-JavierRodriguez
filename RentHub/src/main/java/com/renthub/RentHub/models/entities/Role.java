/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author javie
 */
@Entity
@Table(name="roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrole;

    @Column(unique = true)
    @NotBlank
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getIdroles() {
        return idrole;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
