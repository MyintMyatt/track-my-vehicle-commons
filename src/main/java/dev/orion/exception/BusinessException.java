package dev.orion.exception;

import dev.orion.common.constant.ErrorType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException implements BaseException {
    private final ErrorType errorType;
    private final ExceptionMessageHolder messageHolder;

    public BusinessException(ExceptionMessageHolder messageHolder) {
        this.messageHolder = messageHolder;
        this.errorType = null;
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


