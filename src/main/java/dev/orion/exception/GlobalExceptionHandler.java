package dev.orion.exception;

import dev.orion.common.constant.ErrorType;
import dev.orion.common.model.ApiResponse;
import dev.orion.common.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handle(BusinessException ex){
        log.error("BusinessException", ex);
        return ApiResponse.error(
                new ErrorResponse(
                        ex.getErrorType() != null ? ex.getErrorType() : ErrorType.Business,
                        getMessage(ex)
                )
        );
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
