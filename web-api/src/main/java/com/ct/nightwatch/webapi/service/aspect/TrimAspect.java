package com.ct.nightwatch.webapi.service.aspect;

import com.ct.nightwatch.webapi.service.trimmer.StringTrimmer;
import com.ct.nightwatch.webapi.service.trimmer.annotation.Trim;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
public class TrimAspect {

    @Before("execution(* *(.., @com.ct.nightwatch.webapi.service.trimmer.annotation.Trim (*), ..))")
    public void inspect(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String[] parameterNames = signature.getParameterNames();
        Object[] arguments = joinPoint.getArgs();

        Map<String, Object> parameters = IntStream.range(0, parameterNames.length)
                .boxed()
                .collect(Collectors.toMap(i -> parameterNames[i], i -> arguments[i]));

        Arrays.stream(signature.getMethod().getParameters())
                .filter(parameter -> parameter.isAnnotationPresent(Trim.class))
                .forEach(parameter -> StringTrimmer.trim(parameters.get(parameter.getName())));
    }

}
