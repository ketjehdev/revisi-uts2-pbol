////maria gresia plena br purba
////235314094
package org.example.revisi_uts2_094.config;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final JwtUtility jwtUtility;
//    private final UserDetailsService userDetailsService;
//
//    // Constructor injection
//    @Autowired
//    public JwtFilter(JwtUtility jwtUtility, UserDetailsService userDetailsService) {
//        this.jwtUtility = jwtUtility;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//        String requestPath = request.getServletPath();
//        System.out.println("Requested URL: " + requestPath);
//
//        // Bypass JWT Validation for specific paths
//        if (shouldBypassJwtValidation(requestPath)) {
//            System.out.println("Bypassing JWT validation for: " + requestPath);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // Check Authorization Header
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try {
//                Claims claims = jwtUtility.validateToken(token);
//                request.setAttribute("username", claims.getSubject());
//                request.setAttribute("role", claims.get("role"));
//                System.out.println("Token valid. User: " + claims.getSubject() + ", Role: " + claims.get("role"));
//            } catch (Exception e) {
//                System.out.println("JWT Validation failed: " + e.getMessage());
//                writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
//                return;
//            }
//        } else {
//            System.out.println("No Authorization header present or invalid");
//            writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing or invalid");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean shouldBypassJwtValidation(String requestPath) {
//        // Add all paths that should bypass JWT validation here
//        return requestPath.startsWith("/auth/") || requestPath.equals("/h2-console") ||
//                requestPath.startsWith("/css/") || requestPath.startsWith("/js/") ||
//                requestPath.startsWith("/images/") || requestPath.equals("/error");
//    }
//
//    private void writeErrorResponse(HttpServletResponse response, int status, String errorMessage) throws IOException {
//        response.setStatus(status);
//        response.setContentType("application/json");
//        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
//    }
//}



import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
        String authorizationHeader = request.getHeader("Authorization");
        String requestPath = request.getServletPath();
        System.out.println("Requested URL: " + requestPath);

        if (requestPath.equals("/auth/login") || requestPath.equals("/h2-console")) {
            System.out.println("Bypassing JWT validation for /auth/login");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Masuk get header" + authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            System.out.println("Validating JWT Token :" + token);
            try {
                Claims claims = jwtUtility.validateToken(token);
                request.setAttribute("username", claims.getSubject());
                request.setAttribute("role", claims.get("role"));
                response.addHeader("role", claims.get("role", String.class));

                System.out.println("Token valid. User: " + claims.getSubject() + ", Role: " + claims.get("role"));
            } catch (Exception e) {
                System.out.println("JWT Validation failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            System.out.println("No Authorization header present");
        }
        filterChain.doFilter(request, response);
    }
}
