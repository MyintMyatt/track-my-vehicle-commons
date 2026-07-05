package dev.orion.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException {
    public SecurityException(@Nullable String msg) {
        super(msg);
    }
}
