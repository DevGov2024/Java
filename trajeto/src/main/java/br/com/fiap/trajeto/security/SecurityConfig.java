package br.com.fiap.trajeto.security;


import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
private TokenVerify tokenVerify;


    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf ->
                        csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.GET, "/api/trajeto/*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/trajeto").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/trajeto").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/trajeto/*").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/trajeto/*").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(tokenVerify, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
    @Bean
public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}

@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


     }

