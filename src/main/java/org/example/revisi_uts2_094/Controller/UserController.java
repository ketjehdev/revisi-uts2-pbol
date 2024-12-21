//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Controller;

import org.example.revisi_uts2_094.Model.User;
import org.example.revisi_uts2_094.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Edit anggota perpustakaan
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Hapus anggota perpustakaan
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Lihat semua anggota
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
