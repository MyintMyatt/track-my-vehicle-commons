package dev.orion.commons.aop;

import dev.orion.commons.exception.ValidationException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Slf4j
@Aspect
@Configuration
public class ValidationAspect {

    @PostConstruct
    public void init() {
        log.info("ValidationAspect Created.");
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void apiMethod(){}

    @Before(value = "apiMethod() and args(..,result)", argNames = "result")
    public void handle(BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
    }
}
