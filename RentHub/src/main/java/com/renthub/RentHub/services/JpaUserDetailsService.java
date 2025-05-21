package com.renthub.RentHub.services;


import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        System.out.println("Roles para el usuario " + username + ": " + user.getRoles());
        System.out.println("Roles del usuario: " + user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(), //Cuenta habilitada                
                true, // Cuenta no expirada: boolean accountNonExpired
                true, // Credenciales no expiradas: boolean credentialsNonExpired
                true, // Cuenta no bloqueada: boolean accountNonLocked
                getAuthorities(user) // Convierte los roles a un formato compatible con Spring Security
        );
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  