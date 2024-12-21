////package org.example.revisi_uts2_094.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
////        return http
////                .csrf(csrf -> csrf.disable()) // Disable CSRF
////                .sessionManagement(session -> session.sessionCreationPolicy(
////                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS)) // Stateless session
////                .authorizeHttpRequests(auth -> auth
////
////                        // Public endpoints
////                        .requestMatchers("/auth/**", "/login", "/css/**", "/js/**", "/images/**").permitAll()
////
////                        // Menu endpoint for authenticated users
////                        .requestMatchers("/Menu").hasAnyRole("USER", "ADMIN")
////
////                        // Admin-only endpoints
////                        .requestMatchers("/admin/**", "/books/**", "/users/**").hasRole("ADMIN")
////
////                        // User-only endpoints
////                        .requestMatchers("/user/**", "/borrow/my", "/borrow", "/return").hasRole("USER")
////
////                        // Common access endpoints
////                        .requestMatchers("/", "/error").permitAll()
////
////                        // Secure all other endpoints
////                        .anyRequest().authenticated()
////                )
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
////                .build();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
////        return authConfig.getAuthenticationManager();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new InMemoryUserDetailsManager(
////                User.withUsername("user")
////                        .password(passwordEncoder().encode("password"))
////                        .roles("USER")
////                        .build(),
////                User.withUsername("admin")
////                        .password(passwordEncoder().encode("admin"))
////                        .roles("ADMIN")
////                        .build()
////        );
////    }
////}
////
//
//package org.example.revisi_uts2_094.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF
//                .sessionManagement(session -> session.sessionCreationPolicy(
//                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS)) // Stateless session
//                .authorizeHttpRequests(auth -> auth
//
//                        // Public endpoints
//                        .requestMatchers("/auth/**", "/login", "/css/**", "/js/**", "/images/**").permitAll()
//
//                        // Menu endpoint for authenticated users
//                        .requestMatchers("/Menu").permitAll()
//
//                        // Admin-only endpoints
//                        .requestMatchers("/admin/**", "/books/**", "/users/**").permitAll()
//
//                        // User-only endpoints
//                        .requestMatchers("/user/**", "/borrow/my", "/borrow", "/return").permitAll()
//
//                        // Common access endpoints
//                        .requestMatchers("/", "/error").permitAll()
//
//                        // Secure all other endpoints
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER")
//                        .build(),
//                User.withUsername("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles("ADMIN")
//                        .build()
//        );
//    }
//}
//


package org.example.revisi_uts2_094.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Endpoint tanpa autentikasi
                        .requestMatchers("/login", "/auth/login", "/h2-console/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/login.html","/books.html", "/user.html", "/return.html", "/borrow.html", "/Menu","/js/**",
                                 "/books/**", "/borrow/**", "/users/**","/return/**","/css/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/login.html","/books.html", "/user.html", "/return.html", "/borrow.html", "/Menu","/js/**",
                                "/books/**", "/borrow/**", "/users/**","/return/**","/css/**").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/books.html","/books/**", "/user.html", "/users/**", "/css/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/books.html","/books/**", "/user.html", "/users/**", "/css/**").permitAll()

                        // Endpoint yang membutuhkan autentikasi
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
