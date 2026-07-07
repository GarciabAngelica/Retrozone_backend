package com.Retrozone.retrozone_bd.controller;

import com.Retrozone.retrozone_bd.dto.UsersDTO;
import com.Retrozone.retrozone_bd.model.Users;
import com.Retrozone.retrozone_bd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public List<UsersDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UsersDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersDTO createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public UsersDTO updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUserById(id, user);
    }
    @DeleteMapping("/{id}")
    public UsersDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
    @PostMapping("/login")
    public ResponseEntity<UsersDTO> login(@RequestBody Users user) {
        return userService.loginUser(user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}