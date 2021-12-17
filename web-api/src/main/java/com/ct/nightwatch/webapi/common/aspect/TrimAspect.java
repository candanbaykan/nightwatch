package com.ct.nightwatch.webapi.common.aspect;

import com.ct.nightwatch.webapi.common.utility.trimmer.StringTrimmer;
import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

@Aspect
@Component
public class TrimAspect {

    @Around("execution(* *(.., @com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim (*), ..))")
    public Object inspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        Object[] arguments = proceedingJoinPoint.getArgs();

        IntStream.range(0, parameters.length)
                .filter(i -> parameters[i].isAnnotationPresent(Trim.class))
                .forEach(i -> arguments[i] = StringTrimmer.trim(arguments[i]));

        return proceedingJoinPoint.proceed(arguments);
    }
}
