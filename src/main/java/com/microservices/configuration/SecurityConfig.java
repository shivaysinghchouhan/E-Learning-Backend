package com.microservices.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration; 
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; 
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CORS और CSRF सेटिंग्स
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())

            // Endpoint authorization
            .authorizeRequests(auth -> auth
                .antMatchers("/auth/login", "/user/api/saveUserDetails").permitAll()
                .antMatchers("/test").authenticated()
                .anyRequest().authenticated()
            )
            // Unauthorized handling
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            // Stateless session (JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // JWT filter को UsernamePasswordAuthenticationFilter से पहले चलाओ
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * CORS configuration bean:
     * - React ऐप (http://localhost:3000) से आने वाली requests allow करता है
     * - GET, POST, PUT, DELETE, OPTIONS सभी HTTP methods allow
     * - सभी headers (जिनमें Authorization header भी शामिल है) allow
     * - credentials (cookies/token) भी allow
     */
   /* @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Java 8 compatible lists
        List<String> allowedOrigins = Arrays.asList("http://localhost:3000");
        List<String> allowedMethods = Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS");
        List<String> allowedHeaders = Arrays.asList("*");

        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(allowedMethods);
        config.setAllowedHeaders(allowedHeaders);
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // सभी paths पर ये CORS नियम लागू होंगे
        source.registerCorsConfiguration("/**", config);
        return source;
    }*/
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allowed origins: React app URLs
        config.setAllowedOrigins(Arrays.asList(
        	    "http://localhost:3000",
        	    "http://18.206.229.216" //

        	));

        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
}
