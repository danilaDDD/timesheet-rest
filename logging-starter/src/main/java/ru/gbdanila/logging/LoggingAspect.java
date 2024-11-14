package ru.gbdanila.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.event.Level;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {
    @Data
    @AllArgsConstructor
    private static class ParameterDto{
        private Class<?> paramType;
        private Object paramValue;

        @Override
        public String toString() {
            return String.format("{%s=%s}", paramType.getName(), paramValue.toString());
        }
    }

    private final LoggingConfigurationProperties properties;

    @Pointcut("@annotation(ru.gbdanila.logging.Logging)")
    public void loggingMethodPointcut(){}

    @Pointcut("@within(ru.gbdanila.logging.Logging)")
    public void loggingTypePointcut(){}

    @Around(value = "loggingMethodPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();
        Method method = Arrays.stream(pjp.getThis().getClass().getMethods())
                .filter(it -> it.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] values = pjp.getArgs();
        List<ParameterDto> parameterDtos = new ArrayList<>();

        for(int i = 0; i < parameterTypes.length; i++){
            parameterDtos.add(new ParameterDto(parameterTypes[i], values[i]));
        }

        if(properties.isPrintArgs()){
            print("method signature #{} method arg values #{}", parameterDtos, pjp.getArgs());
        }else {
            print("method name #{}", methodName);
        }

        try{
            Object result = pjp.proceed();
            print("result: #{} if method #{}", result, methodName);
            return result;
        }finally {
            print("end method #{}", methodName);
        }
    }

    private void print(String message, Object... args){
        Level level = properties.getLevel();
       log.atLevel(level).log(message, args);
    }

}




