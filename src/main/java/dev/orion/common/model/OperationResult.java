package dev.orion.common.model;

public record OperationResult<T> (
        T id,
        String message
){
}
