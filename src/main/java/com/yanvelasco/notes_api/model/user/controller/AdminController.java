package com.yanvelasco.notes_api.model.user.controller;

import com.yanvelasco.notes_api.model.user.dto.UserDTO;
import com.yanvelasco.notes_api.model.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update-role")
    public ResponseEntity<UserDTO> updateUserRole(@RequestParam UUID userId, @RequestParam String newRoleName) {
        return userService.updateUserRole(userId, newRoleName);
    }
}