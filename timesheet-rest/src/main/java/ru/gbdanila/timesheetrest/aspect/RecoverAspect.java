package ru.gbdanila.timesheetrest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RecoverAspect {

    @Around("@annotation(ru.gbdanila.timesheetrest.aspect.Recover)")
    public Object recoverMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if(method.isAnnotationPresent(Recover.class)){
            Recover recover = method.getAnnotation(Recover.class);
            Class<? extends Throwable>[] noRecoverFor = recover.noRecoverFor();

            if(method.getReturnType().getName().equals("void")) {
                try{
                    joinPoint.proceed();
                }catch (Throwable e){
                    throwNoRecoverExceptionsIfNeed(e, noRecoverFor);
                    logException(e, method);
                }

            }else {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throwNoRecoverExceptionsIfNeed(e, noRecoverFor);
                    logException(e, method);
                    return getDefault(method);
                }
            }

        }else{
            throw new IllegalArgumentException("Recover annotation may be used with method!");
        }

        return null;
    }

    private void throwNoRecoverExceptionsIfNeed(Throwable th,  Class<? extends Throwable>[] noRecoverFor) throws Throwable {
        Class<? extends Throwable> throwClass = th.getClass();
        for(var noRecoverClazz: noRecoverFor){
            if(noRecoverClazz.isAssignableFrom(throwClass)){
                throw th;
            }
        }
    }

    private void logException(Throwable e, Method method) {
        log.error("throw error {}  message {} with method {}", e.getClass(), e.getMessage(), method.getName());
    }

    private Object getDefault(Method method) {
        Class<?> returnType = method.getReturnType();
        if(returnType.isPrimitive()){
            return switch (returnType.getName()){
                case "int" -> 0;
                case "double" -> 0.0;
                case "long" -> 0L;
                case "short", "byte" -> String.valueOf(0);
                default -> throw new IllegalArgumentException("return void is not available");
            };
        }
        return null;
    }
}
