package dev.orion.commons.exception;

import dev.orion.commons.constant.ErrorType;
import dev.orion.commons.exception.auth.OtpException;
import dev.orion.commons.exception.auth.SecurityException;
import dev.orion.commons.model.ApiResponse;
import dev.orion.commons.model.ErrorResponse;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handle(BusinessException ex){
        log.error("BusinessException", ex);
        List<String> msg;
        try{
            msg = getMessage(ex);
        }catch (Exception e){
           msg = List.of(ex.getMessage());
        }
        return ApiResponse.error(
                new ErrorResponse(
                        ex.getErrorType() != null ? ex.getErrorType() : ErrorType.Business,
                        msg
                )
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handle(ValidationException ex){
        log.error("Validation", ex);
        return ApiResponse.error(new ErrorResponse(ErrorType.Validation,
                ex.getMessages()));
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS)
    public ApiResponse<ErrorResponse> handle(OtpException ex){
        log.error("OtpException", ex);
        return ApiResponse.error(new ErrorResponse(ErrorType.Security, getMessage(ex)));
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse<ErrorResponse> handle(SecurityException ex){
        log.error("SecurityException", ex);
        return ApiResponse.error(new ErrorResponse(ErrorType.Security, getMessage(ex)));
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse<ErrorResponse> handle(AccessDeniedException ex){
        log.error("SecurityException", ex);
        return ApiResponse.error(new ErrorResponse(ErrorType.Security, "You have no permission for this operation."));
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse<ErrorResponse> handle(MissingRequestValueException ex){
        log.error("MissingRequestValueException", ex);
        return ApiResponse.error(new ErrorResponse(ErrorType.Security, "Missing Request Some Value"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handle(HttpMessageNotReadableException ex) {

        return ApiResponse.error(
                new ErrorResponse(
                        ErrorType.Validation,
                        List.of(ex.getMostSpecificCause().getMessage())
                )
        );
    }

    /**
     * Catches exceptions thrown by CompletableFuture.join() inside the Aspect
     */
    @ExceptionHandler(CompletionException.class)
    public ApiResponse<ErrorResponse> handleCompletionException(CompletionException ex) {
        log.error("Async execution failed", ex);
        Throwable cause = ex.getCause();

        if (cause instanceof StatusRuntimeException statusEx) {
            return handleGrpcStatusRuntimeException(statusEx);
        }

        return ApiResponse.error(new ErrorResponse(ErrorType.Business, List.of(ex.getMessage())));
    }

    /**
     * Converts gRPC status codes into appropriate HTTP API Responses
     */
    @ExceptionHandler(StatusRuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handleGrpcStatusRuntimeException(StatusRuntimeException ex) {
        log.error("gRPC client call failed: {}", ex.getStatus());

        Status.Code code = ex.getStatus().getCode();
        String description = ex.getStatus().getDescription();

        if (code == Status.Code.NOT_FOUND) {
            return ApiResponse.error(new ErrorResponse(ErrorType.Business, description));
        } else if (code == Status.Code.PERMISSION_DENIED || code == Status.Code.UNAUTHENTICATED) {
            return ApiResponse.error(new ErrorResponse(ErrorType.Security, List.of("Access denied downstream: " + description)));
        }

        return ApiResponse.error(new ErrorResponse(ErrorType.Business, List.of("Downstream service error: " + description)));
    }



    private List<String> getMessage(BaseException ex){
        return ex.getMessageHolder().getMessages().stream().map(m -> {
            try{
                return messageSource.getMessage(m.getCode(), m.getParams(), LocaleContextHolder.getLocale());
            }catch (Exception e){
                return m.getCode();
            }
        }).toList();
    }
}
