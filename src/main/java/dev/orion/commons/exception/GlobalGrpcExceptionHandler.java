package dev.orion.commons.exception;

import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class GlobalGrpcExceptionHandler implements GrpcExceptionHandler {
    @Override
    public StatusException handleException(Throwable ex) {
        if(ex instanceof ResourceNotFoundException){
            return Status
                    .NOT_FOUND
                    .withCause(ex)
                    .withDescription(ex.getMessage())
                    .asException();
        } else if (ex instanceof  BusinessException b) {
            return Status
                    .INTERNAL
                    .withCause(ex)
                    .withDescription(b.getMessage())
                    .asException();
        }else {
            return Status
                    .INTERNAL
                    .withCause(ex)
                    .withDescription(ex.getMessage())
                    .asException();
        }

    }
}
