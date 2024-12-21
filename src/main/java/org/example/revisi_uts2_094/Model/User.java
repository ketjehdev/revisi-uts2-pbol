package org.example.revisi_uts2_094.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private LocalDateTime membershipDate;

    public User(){

    }
    public User(String username, String password, Role role, String email, LocalDateTime membershipDate) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.membershipDate = membershipDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDateTime membershipDate) {
        this.membershipDate = membershipDate;
    }
}

