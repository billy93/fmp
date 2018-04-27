package com.atibusinessgroup.fmp.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class UserErrorException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserErrorException(String message) {
        super(message);
    }

    public UserErrorException(String message, Throwable t) {
        super(message, t);
    }
}
