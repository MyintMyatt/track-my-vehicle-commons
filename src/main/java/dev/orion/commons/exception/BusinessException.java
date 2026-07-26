package dev.orion.commons.exception;

import dev.orion.commons.constant.ErrorType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException implements BaseException {
    private ErrorType errorType;
    private ExceptionMessageHolder messageHolder;

    public BusinessException(ExceptionMessageHolder messageHolder) {
        this.messageHolder = messageHolder;
        this.errorType = null;
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(ErrorType errorType,
                             ExceptionMessageHolder messageHolder) {
        this.errorType = errorType;
        this.messageHolder = messageHolder;
    }

    @Override
    public ExceptionMessageHolder getMessageHolder() {
        return this.messageHolder;
    }
}


