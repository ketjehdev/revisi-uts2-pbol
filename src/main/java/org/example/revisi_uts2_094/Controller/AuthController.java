//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.revisi_uts2_094.Model.AuthRequest;
import org.example.revisi_uts2_094.Model.AuthResponse;
import org.example.revisi_uts2_094.Model.User;
import org.example.revisi_uts2_094.Service.UserService;
import org.example.revisi_uts2_094.config.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Login attempt for username: " + authRequest.getUsername());
        User user = userService.findByUsername(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String token = jwtUtility.generateToken(user.getUsername(), String.valueOf(user.getRole()));
            System.out.println("Login successful for username: " + authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        System.out.println("Login failed for username: " + authRequest.getUsername());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
