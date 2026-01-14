//package com.example.demo.security;
//
//import com.example.demo.security.CustomUserDetailsService;
//import com.example.demo.security.JwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final CustomUserDetailsService customUserDetailsService;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(customUserDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .sessionManagement(session -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .authenticationProvider(authenticationProvider())
////                .authorizeHttpRequests(auth -> auth
////                        // Public endpoints - authentication
////                        .requestMatchers("/api/auth/**").permitAll()
////                        .requestMatchers("**").permitAll()
////
////
////                        // Public GET endpoints - students
////                        .requestMatchers(HttpMethod.GET, "/api/students/**").permitAll()
////
////                        // Protected endpoints - students (POST, PUT, DELETE require ADMIN)
////                        .requestMatchers(HttpMethod.POST, "/api/students/**").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.PUT, "/api/students/**").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
////
////                        // Public GET endpoints - preferences
////                        .requestMatchers(HttpMethod.GET, "/api/preferences/**").permitAll()
////
////                        // Protected endpoints - preferences (POST, PUT, DELETE require ADMIN or STUDENT)
////                        .requestMatchers(HttpMethod.POST, "/api/preferences/**").hasAnyRole("ADMIN", "STUDENT")
////                        .requestMatchers(HttpMethod.PUT, "/api/preferences/**").hasAnyRole("ADMIN", "STUDENT")
////                        .requestMatchers(HttpMethod.DELETE, "/api/preferences/**").hasAnyRole("ADMIN", "STUDENT")
////
////                        // All other requests require authentication
////                        .anyRequest().authenticated()
////                )
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(auth -> auth
//                            .anyRequest().permitAll()
//                    );
//
//            return http.build();
//}
//
//}