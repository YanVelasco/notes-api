package com.yanvelasco.notesapi.domain.user.controller;

import com.yanvelasco.notesapi.domain.user.dto.LoginRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.LoginResponseDTO;
import com.yanvelasco.notesapi.domain.user.service.AuthorizationService;
import com.yanvelasco.notesapi.exceptions.GenareteTokenException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthorizationService authorizationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) throws GenareteTokenException {
        authorizationService.setAuthenticationManager(authenticationManager);
        var tokenResponse = authorizationService.authenticateAndGenerateToken(data);
        return ResponseEntity.ok(tokenResponse);
    }
}