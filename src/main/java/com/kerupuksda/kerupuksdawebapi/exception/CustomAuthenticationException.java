package com.kerupuksda.kerupuksdawebapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CustomAuthenticationException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

    public CustomAuthenticationException() {
        super("Invalid login");
    }
	
    public CustomAuthenticationException(String message) {
        super(message);
    }

    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}