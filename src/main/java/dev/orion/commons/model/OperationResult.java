package dev.orion.commons.model;

public record OperationResult<T> (
        T id,
        String message
){
}
