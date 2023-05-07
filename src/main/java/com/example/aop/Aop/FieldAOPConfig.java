package com.example.aop.Aop;

import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.OnValidate;
import com.example.aop.Config.FieldTriggerScan;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

@Component
@Aspect
public class FieldAOPConfig {

    @Pointcut("execution(* com.example.aop.Record.TestRecord+.Validate(..))")
    public void OnValidate() {}

    @Around("OnValidate()")
    public Object OnDoTest(ProceedingJoinPoint pjp) throws Throwable {

        if (!Objects.equals(pjp.getKind(), JoinPoint.METHOD_EXECUTION)) return pjp.proceed();

        Object arg = pjp.getArgs()[0];

        Class<?> table = GetClass(arg);

        Field field = table.getDeclaredField(arg.toString());

        HandleValidateTrigger(field,table);

        return pjp.proceed();
    }

    private Class<?> GetClass(Object arg) throws ClassNotFoundException {

        String classpath = arg.getClass().getName().replace("$Fields", "");

        return Class.forName(classpath);
    }

    private void HandleValidateTrigger(Field field,Class<?> table) {

        if (!field.isAnnotationPresent(OnValidate.class)) return;

        OnValidate onValidate = Objects.requireNonNull(field.getAnnotation(OnValidate.class));

        if (onValidate.Value().name().isBlank()) return;

        String methodName = onValidate.Value().name();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FieldTriggerScan.class);

        Collection<Object> beans = applicationContext.getBeansWithAnnotation(FieldTrigger.class).values();

        TriggerUtils.InvokeFieldMethod(methodName,beans,table);

    }
}
