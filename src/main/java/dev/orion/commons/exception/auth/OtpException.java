package dev.orion.commons.exception.auth;

import dev.orion.commons.exception.BaseException;
import dev.orion.commons.exception.ExceptionMessageHolder;

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
