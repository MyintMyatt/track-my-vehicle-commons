package dev.orion.commons.utils;

import dev.orion.commons.model.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationResultFactory {
    private final MessageSource messageSource;

    public <T>OperationResult<T> created(T id, String domain){
        return new OperationResult<>(id, messageSource.getMessage("app.resource.created", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> updated(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.updated", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> deleted(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.deleted", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> requested(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.requested", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> requestedUpdate(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.updated", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> requestedAgain(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.requestedAgain", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> requestCancelled(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.request.cancel", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> approved(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.approved", new Object[]{domain}, null));
    }

    public <T> OperationResult<T> rejected(T id, String domain) {
        return new OperationResult<>(id, messageSource.getMessage("app.resource.rejected", new Object[]{domain}, null));
    }
}
