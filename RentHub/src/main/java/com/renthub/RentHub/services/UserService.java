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
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    
     public UserService(UserRepository u, RoleRepository r, PasswordEncoder e) {
        this.userRepository = u;
        this.roleRepository = r;
        this.passwordEncoder  = e;
      }
    
    
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
    
    /** Registro público */
  public User register(String username, String rawPassword,
                       String name, String lastName) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("El usuario ya existe");
    }
    User u = new User();
    u.setUsername(username);
    u.setPassword(passwordEncoder.encode(rawPassword));
    u.setName(name);
    u.setLastName(lastName);
    // rol por defecto
    Role roleUser = roleRepository.findByName("ROLE_AUTONOMA")
                     .orElseThrow(() -> new RuntimeException("Roles no inicializados"));
    u.getRoles().add(roleUser);
    return userRepository.save(u);
  }

  /** Spring Security: carga de usuario para la autenticación */
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User u = userRepository.findByUsername(username)
             .orElseThrow(() -> new UsernameNotFoundException(username));
    return new org.springframework.security.core.userdetails.User(
      u.getUsername(),
      u.getPassword(),
      u.isEnabled(),
      true, true, true,
      u.getRoles().stream()
         .map(r -> new SimpleGrantedAuthority(r.getName()))
         .collect(Collectors.toList())
    );
  }
    
    

}
