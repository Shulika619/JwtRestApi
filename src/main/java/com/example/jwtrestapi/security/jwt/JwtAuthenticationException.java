package com.example.jwtrestapi.security.jwt;


import org.springframework.security.core.AuthenticationException;

// AuthenticationException из security.core.
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
