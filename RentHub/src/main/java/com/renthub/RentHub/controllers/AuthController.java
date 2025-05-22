/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.controllers;

import com.renthub.RentHub.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javie
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;
  private final AuthenticationManager authManager;

  public AuthController(UserService us, AuthenticationManager am) {
    this.userService  = us;
    this.authManager = am;
  }

  record RegisterDto(String username, String password, String name, String lastName) {}
  record LoginDto(String username, String password) {}
  record LoginResponse(String username, List<String> roles) {}

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterDto dto) {
    userService.register(dto.username(), dto.password(), dto.name(), dto.lastName());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginDto dto) {
    UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
    Authentication auth = authManager.authenticate(token);
    @SuppressWarnings("unchecked")
    var authorities = auth.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.toList());
    return ResponseEntity.ok(new LoginResponse(dto.username(), authorities));
  }
}
