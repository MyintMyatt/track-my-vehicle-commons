package dev.orion.exception;

import dev.orion.common.constant.ErrorType;

public class ServiceException extends RuntimeException implements BaseException{
    private final ErrorType errorType;
    private final ExceptionMessageHolder messageHolder;

    public ServiceException(ExceptionMessageHolder messageHolder) {
        this.messageHolder = messageHolder;
        this.errorType = null;
    }

    public ServiceException(ErrorType errorType,
                             ExceptionMessageHolder messageHolder) {
        this.errorType = errorType;
        this.messageHolder = messageHolder;
    }

    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return this.messageHolder;
    }
}
