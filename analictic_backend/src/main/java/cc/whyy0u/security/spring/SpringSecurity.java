package cc.whyy0u.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cc.whyy0u.security.auth.jwt.JwtAuthenticationFilter;
import cc.whyy0u.service.user.custom.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
class SpringSecurity{

  CustomUserDetailsService userService;
 
  JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
    public SpringSecurity(CustomUserDetailsService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http.cors().and().csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
      .requestMatchers("/api/auth/**").permitAll()
      .anyRequest().authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider())
      .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService()); 
        provider.setPasswordEncoder(passwordEncoder()); 
        return provider;
    }

  @Bean
  public AuthenticationManager authM(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
