package com.yanvelasco.notesapi.domain.user.service;

import com.yanvelasco.notesapi.domain.user.dto.LoginRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.LoginResponseDTO;
import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.infra.security.jwt.TokenService;
import com.yanvelasco.notesapi.exceptions.GenareteTokenException;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    @Setter
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public LoginResponseDTO authenticateAndGenerateToken(LoginRequestDTO data) throws GenareteTokenException {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (UserEntity) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        if (token == null) {
            throw new GenareteTokenException("Error generating token");
        }
        return new LoginResponseDTO(token);
    }
}