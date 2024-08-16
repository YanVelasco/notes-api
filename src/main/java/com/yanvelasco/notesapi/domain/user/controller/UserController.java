package com.yanvelasco.notesapi.domain.user.controller;

import com.yanvelasco.notesapi.domain.user.dto.UserRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.UserResponseDTO;
import com.yanvelasco.notesapi.domain.user.service.CreateUserService;
import com.yanvelasco.notesapi.domain.user.service.PutUserById;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserService createUserService;
    private final PutUserById putUserById;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return createUserService.create(userRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String id, @RequestBody UserRequestDTO userRequestDTO) {
        return putUserById.update(id, userRequestDTO);
    }
}
