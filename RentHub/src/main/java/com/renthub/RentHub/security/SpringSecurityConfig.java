package com.renthub.RentHub.security;


import com.renthub.RentHub.services.JpaUserDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SpringSecurityConfig {
//métodos de configuración de seguridad    

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//   @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests((authz) -> authz
////                .requestMatchers("/users").permitAll()
//                .requestMatchers("/productos").permitAll()
//                .requestMatchers(HttpMethod.GET, "/users").permitAll()
//                .requestMatchers("/users/register").permitAll()
////                .requestMatchers("/users/**").permitAll()
//                .anyRequest().authenticated())
//                 .httpBasic(withDefaults()) //AUTENTICACIÓN BÁSICA SE REQUIERE EN CADA ENDPOINT PRIVADO
//                .csrf(config -> config.disable())
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
    
    

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                 .csrf(config -> config.disable())
//                .authorizeHttpRequests((authz) -> authz
////                   establecemos la política seguridad para las distintas url.Por ejemplo:  
//                    .requestMatchers("/users").permitAll()
//                    .requestMatchers("/clientes").hasAuthority("ROLE_USER")
//                    .requestMatchers("/pedidos").hasAuthority("ROLE_USER")
//                    .requestMatchers("/clientes/nuevo").hasAuthority("ROLE_MANAGER")
//                    .requestMatchers("/pedidos/nuevo").hasAuthority("ROLE_MANAGER")   
//                    .requestMatchers("/clientes/editar/*","/clientes/eliminar/*" ).hasAuthority("ROLE_ADMIN")
//                    .requestMatchers("/pedidos/editar/*","/pedidos/eliminar/*").hasAuthority("ROLE_ADMIN")
//                    //.requestMatchers("/pedidos/**").hasAuthority("ROLE_USER")
//                    .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())//  Habilita el login por defecto de Spring Security
//                //Si queremos el login personalizado:
////                .formLogin(login -> login  
////                      .loginPage("/login") // url de la página personalizada de login
////                      .defaultSuccessUrl("/", true) // Dónde quieres que se redirija tras login exitoso
////                      .permitAll()   //para que la página de login sea pública
////                 )
//                .logout(logout -> logout
//                    .logoutUrl("/logout")   //establece la url para hacer el logout
//                    .logoutSuccessUrl("/")   //Tras el logout redirige a index
//                    .invalidateHttpSession(true) // Invalidar sesión
//                    .deleteCookies("JSESSIONID") // Eliminar cookies de sesión
//                    .permitAll()  //permite que el logout sea público
//                )
//		//IMPORTANTE: logout se realiza con un método post así que el botón de desconexión no puede ser
//                //un enlace sino un form:
//		/* <form th:action="@{/logout}" method="post">
//                       <button type="submit">Cerrar sesión</button>
//                   </form>
//		*/
//		//No se necesita controlador para logout porque Spring Security ya maneja el logout automáticamente.
//                .exceptionHandling(exception -> exception.accessDeniedPage("/clientes/error"));
//                  //en esta línea especificamos qué hacer con los errores. En este caso en el controlador
//                  //de user debe existir un handler con esa dirección que mande el error a una plantilla
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .cors(withDefaults())
          // 1) Deshabilita CSRF para tu API REST
          .csrf(csrf -> csrf.disable())
          // 2) Define qué rutas son públicas
          .authorizeHttpRequests(auth -> auth
            // Recursos estáticos (imágenes) públicos
            .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
            // Permite GET (y opcionalmente POST, PUT, DELETE) sobre /vehiculos y subrutas
            .requestMatchers(HttpMethod.GET,    "/vehiculos",      "/vehiculos/**").permitAll()
            .requestMatchers(HttpMethod.POST,   "/vehiculos",      "/vehiculos/**").permitAll()
            .requestMatchers(HttpMethod.PUT,    "/vehiculos/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/vehiculos/**").permitAll()
            .requestMatchers(HttpMethod.GET,    "/test-email").permitAll()
            .requestMatchers(HttpMethod.GET, "/users/me").authenticated()
            .requestMatchers(HttpMethod.GET, "/users/me/direccion").authenticated()
            .requestMatchers(HttpMethod.POST, "/users/me/direccion").authenticated()
            .requestMatchers(HttpMethod.PUT, "/users/me/direccion").authenticated()
        // Cancelar renovación requiere autenticación
            .requestMatchers(HttpMethod.PUT, "/alquiler/**").authenticated()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            // Si tu API estuviera bajo /api/cars, añade aquí también:
            // .requestMatchers("/api/cars/**").permitAll()
            // El resto de rutas requiere autenticación
            .anyRequest().authenticated()
          )
          // 3) Usa HTTP Basic (no formulario) para el resto de endpoints autenticados
          .httpBasic(withDefaults())
                .logout(logout -> logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
                    .permitAll()
                  )
          // 4) Stateless: no mantenemos sesión en servidor
          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    
    

    @Bean  //Nuevo componente que gestiona la autenticación
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Usa el servicio de usuarios
        authProvider.setPasswordEncoder(passwordEncoder()); // Usa BCrypt para verificar contraseñas

        return new ProviderManager(authProvider);
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(List.of("http://localhost:4200"));
      config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
      config.setAllowedHeaders(List.of("*"));
      config.setAllowCredentials(true);
      config.setMaxAge(3600L);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      // aplica CORS global a todas las rutas
      source.registerCorsConfiguration("/**", config);
      return source;
    }
    
  
}



















//CON AUTENTICACIÓN BÁSICA S. SECURITY MANEJA TODO AUTOMÁTICAMENTE Y NO HAY QUE HACER LOGIN
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


//}