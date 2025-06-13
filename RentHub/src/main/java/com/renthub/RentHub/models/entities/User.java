/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 120)
    private String username;

    @NotBlank
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "idrole")
    )
    private List<Role> roles;

    private boolean enabled;

    @Transient
    private boolean admin;
    
    
    private String name;
    
    private String lastName;
    
    @OneToOne(optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "iddireccion", 
            referencedColumnName = "iddireccion",
            unique = true, 
            nullable = true 
    )
    private Direccion direccion;
    
    
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties("user")
    private List<Alquiler> alquileres = new ArrayList<>();
    
    
    
    public User(String username, String password) {

        this.username = username;
        this.password = password;
        this.enabled = true;
        this.roles= new ArrayList<>();
    }

    public User(String username, String password, String name, String lastName) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();;
        this.enabled = true;
        this.name = name;
        this.lastName = lastName;
    }

    
    
    
    public User() {
         this.enabled = true;
         this.roles= new ArrayList<>();
    }

    public Long getIduser() {
        return iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

 //   public boolean isAdmin() {
   //     return admin;
    //}

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public void addAlquiler(Alquiler alquiler) {
        alquileres.add(alquiler);
        alquiler.setUser(this);
    }

    public void removeAlquiler(Alquiler alquiler) {
        alquileres.remove(alquiler);
        alquiler.setUser(null);
    }
    
   @Transient
    public boolean isAdmin() {
      return roles.stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));
    }
  
}
