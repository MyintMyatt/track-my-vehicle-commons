package dev.orion.exception.auth;

import dev.orion.exception.BaseException;
import dev.orion.exception.ExceptionMessageHolder;

public class OtpException extends SecurityException implements BaseException {

    public OtpException(String msg){
        super(msg);
    }
    public OtpException(ExceptionMessageHolder messageHolder) {
        super(messageHolder);
    }
    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return super.getMessageHolder();
    }
}
