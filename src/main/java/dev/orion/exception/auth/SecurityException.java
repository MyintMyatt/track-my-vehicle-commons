package dev.orion.exception.auth;

import dev.orion.exception.BaseException;
import dev.orion.exception.ExceptionMessageHolder;
import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException implements BaseException {
    private ExceptionMessageHolder messageHolder;

    public SecurityException(String msg){
        super(msg);
    }
    public SecurityException(ExceptionMessageHolder messageHolder) {
        super(messageHolder.getMessages().getFirst().getCode());
        this.messageHolder = messageHolder;
    }

    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return this.messageHolder;
    }
}
