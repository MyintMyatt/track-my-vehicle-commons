package dev.orion.commons.model;

import dev.orion.commons.constant.ErrorType;

import java.util.List;

public record ErrorResponse(
        ErrorType errorType,
        List<String> messages
) {
    public ErrorResponse(ErrorType errorType, String message) {
        this(errorType, List.of(message));
    }
}
