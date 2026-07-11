package dev.orion.commons.aop;

import dev.orion.commons.exception.ValidationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationAspect {

    @Pointcut(value = "within(org.springframework.web.bind.annotation.RestController)")
    public void apiMethod(){}

    @Before(value = "apiMethod() && args(..,result)", argNames = "result")
    public void handle(BindingResult result){
        if (result.hasErrors()){
            System.err.println("=============");
            throw new ValidationException(result);
        }
    }
}
