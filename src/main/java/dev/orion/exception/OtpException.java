package dev.orion.exception;

import org.jspecify.annotations.Nullable;

public class OtpException extends SecurityException{
    public OtpException(@Nullable String msg) {
        super(msg);
    }
}
