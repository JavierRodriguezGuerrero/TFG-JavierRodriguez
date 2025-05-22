/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.security;

import com.renthub.RentHub.models.entities.Role;
import com.renthub.RentHub.models.repository.RoleRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author javie
 */
@Component
public class DataInitializer implements CommandLineRunner {
  private final RoleRepository roleRepo;
  public DataInitializer(RoleRepository r) { this.roleRepo = r; }
  @Override
  public void run(String... args) {
    List<String> names = List.of("ROLE_ADMIN","ROLE_AUTONOMA","ROLE_EMPRESA");
    for (String n : names) {
      if (roleRepo.findByName(n).isEmpty()) {
        Role r = new Role();
        r.setName(n);
        roleRepo.save(r);
      }
    }
  }
}
