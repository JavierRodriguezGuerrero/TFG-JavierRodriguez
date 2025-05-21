/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;



import com.renthub.RentHub.models.entities.Role;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.repository.RoleRepository;
import com.renthub.RentHub.models.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

/**
 *
 * @author ana
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
     @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
    
    public User save(User user) {        
        List<Role> roles = new ArrayList<>();
        Optional<Role> rolUser = roleRepository.findByName("ROLE_USER");
        roles.add(rolUser.get());
        if (user.isAdmin()) {
            Optional<Role> rolAdmin = roleRepository.findByName("ROLE_ADMIN");
            roles.add(rolAdmin.get());
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    
     public boolean existeUsername(String username) {
       return userRepository.existsByUsername(username);
     }
    
    public User admin(String user) {        
        List<Role> roles = new ArrayList<>();
        Optional<Role> rolAdmin = roleRepository.findByName("ROLE_ADMIN");
        roles.add(rolAdmin.get());
        Optional<User> usu=userRepository.findByUsername(user);
        User usuario=usu.get();
        
        usuario.setRoles(roles);
        return userRepository.save(usuario);
    }

}
