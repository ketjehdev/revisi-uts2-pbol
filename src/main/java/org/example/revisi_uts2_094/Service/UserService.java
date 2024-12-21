//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Service;

import jakarta.annotation.PostConstruct;
import org.example.revisi_uts2_094.Model.Role;
import org.example.revisi_uts2_094.Model.User;
import org.example.revisi_uts2_094.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;

        public User findByUsername(String username) {
            return userRepository.findByUsername(username).orElse(null);
        }
    @PostConstruct
    public void init() {
        try {
            System.out.println("Initializing users...");
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), Role.ADMIN, "admin@gmail.com", LocalDateTime.now()));
                System.out.println("Admin user created.");
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new User("user", passwordEncoder.encode("userpass"), Role.USER, "user@gmail.com", LocalDateTime.now()));
                System.out.println("Regular user created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during UserService initialization", e);
        }
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMembershipDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(updatedUser.getUsername());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setRole(updatedUser.getRole());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}