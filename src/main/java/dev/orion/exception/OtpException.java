package dev.orion.exception;

public class OtpException extends SecurityException implements BaseException{

    public OtpException(ExceptionMessageHolder messageHolder) {
        super(messageHolder);
    }
    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return super.getMessageHolder();
    }
}
