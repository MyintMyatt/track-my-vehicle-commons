package dev.orion.commons.aop;

import dev.orion.commons.annotations.PreAuthorizeController;
import dev.orion.commons.exception.auth.SecurityException;
import dev.orion.grpc.auth.client.PermissionCheckRequest;
import dev.orion.track_my_vehicle_auth_client.AuthClient;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.AccessDeniedException;

@Aspect
@RequiredArgsConstructor
public class PreAuthorizeControllerAspect {

    private final AuthClient authClient;

    @Around("@annotation(preAuthorizeController)")
    public Object preAuthorizeController(ProceedingJoinPoint joinPoint, PreAuthorizeController preAuthorizeController) throws Throwable {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("You must be logged in to access this resource.");
        }
        var request = PermissionCheckRequest.newBuilder()
                .setUsername(authentication.getName())
                .setResource(preAuthorizeController.resource())
                .setAction(preAuthorizeController.action())
                .build();
        var response = authClient.checkPermission(request).join();

        if (!response.getIsGranted()){
            throw new AccessDeniedException("You have no permission to access this resource.");
        }
        return joinPoint.proceed();
    }
}
