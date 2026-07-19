package dev.orion.commons.exception;

public class ResourceNotFoundException extends BusinessException{
    public ResourceNotFoundException(ExceptionMessageHolder messageHolder) {
        super(messageHolder);
    }
}
