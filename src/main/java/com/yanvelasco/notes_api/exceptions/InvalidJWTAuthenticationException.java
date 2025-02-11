package com.yanvelasco.notes_api.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJWTAuthenticationException extends AuthenticationException {
    public InvalidJWTAuthenticationException(String message) {
        super(message);
    }
}
