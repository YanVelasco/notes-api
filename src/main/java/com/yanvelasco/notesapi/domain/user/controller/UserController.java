package com.yanvelasco.notesapi.domain.user.controller;

import com.yanvelasco.notesapi.domain.user.dto.UserRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.UserResponseDTO;
import com.yanvelasco.notesapi.domain.user.service.CreateUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserService createUserService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return createUserService.create(userRequestDTO);
    }
}
