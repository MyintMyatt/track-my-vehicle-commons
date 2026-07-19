package dev.orion.commons.annotations;

import dev.orion.grpc.auth.client.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorizeController {
    String resource();
    Action action();
}
