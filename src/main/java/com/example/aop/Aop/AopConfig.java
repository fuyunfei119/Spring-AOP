package com.example.aop.Aop;

import com.example.aop.Annotation.OnValidate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

@Component
@Aspect
public class AopConfig {

    @Pointcut("execution(* com.example.aop.Record.TestRecord+.Validate(..))")
    public void OnValidate() {}

//    @Before("OnValidate()")
//    public void OnBoforeBeforeValidate(JoinPoint joinPoint) {
//        System.out.println("OnBeforeValidate...............");
//        System.out.println(joinPoint.getSignature());
//    }
//
//    @After("OnValidate()")
//    public void OnAfterAfterValidate() {
//        System.out.println("OnAfterValidate................");
//    }

    @Around("OnValidate()")
    public Object OnDoTest(ProceedingJoinPoint pjp) throws Throwable {

        Object arg = pjp.getArgs()[0];
        String classpath = arg.getClass().getName().replace("$Fields", "");
        Class<?> aClass = Class.forName(classpath);
                Field field = aClass.getDeclaredField(arg.toString());
                if (field.isAnnotationPresent(OnValidate.class)) {
                    System.out.println("should be triggered.");
                }


        Arrays.stream(pjp.getArgs()).forEach(o -> {
//            System.out.println(o);
//            System.out.println(o.getClass().getName());
//
//            String classpath = o.getClass().getName().replace("$Fields", "");
//            System.out.println(classpath);
//            try {
//                Class<?> aClass = Class.forName(classpath);
//                Field field = aClass.getDeclaredField(o.toString());
//                if (field.isAnnotationPresent(OnValidate.class)) {
//                    System.out.println("should be triggered.");
//                }
//            } catch (ClassNotFoundException | NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            }
//
//
//            System.out.println(o.getClass().getSimpleName());
//            System.out.println(o.getClass().getGenericSuperclass().getTypeName());
        });

        Object proceed = pjp.proceed();
        return proceed;
    }
}
