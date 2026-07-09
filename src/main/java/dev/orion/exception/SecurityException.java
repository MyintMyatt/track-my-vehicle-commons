package dev.orion.exception;

import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException implements BaseException{
    private ExceptionMessageHolder messageHolder;

    public SecurityException(ExceptionMessageHolder messageHolder) {
        super(messageHolder.getMessages().getFirst().getCode());
        this.messageHolder = messageHolder;
    }

    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return this.messageHolder;
    }
}
