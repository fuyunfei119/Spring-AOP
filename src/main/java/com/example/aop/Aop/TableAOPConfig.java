package com.example.aop.Aop;

import com.example.aop.Annotation.*;
import com.example.aop.Config.FieldTriggerScan;
import com.example.aop.Config.TableTriggerScan;
import com.example.aop.System.SystemTriggerType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


@Component
@Aspect
public class TableAOPConfig {

    @Pointcut("execution(* com.example.aop.Record.TestRecord+.Modify(..)) || execution(* com.example.aop.Record.TestRecord*.Insert(..))")
    public void OnTriggerTableEvent() {
    }

    @Around("OnTriggerTableEvent()")
    public Object OnDoTest(ProceedingJoinPoint pjp) throws Throwable {

        if (!Objects.equals(pjp.getKind(), JoinPoint.METHOD_EXECUTION)) return pjp.proceed();

        Class<?> table = GetClass(pjp);

        System.out.println(pjp.getSignature().getName());

        switch (pjp.getSignature().getName()) {
            case "Insert" -> HandleInsertTrigger(table);
            case "Modify" -> HandleModifyTrigger(table);
            case "Delete" -> HandleDeleteTrigger(table);
            case "Init" -> HandleInitTrigger(table);
        }


        return pjp.proceed();
    }

    private Class<?> GetClass(ProceedingJoinPoint pjp) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        Object aClass = pjp.getTarget().getClass().getDeclaredMethod("getaClass").invoke(pjp.getTarget());

        String classPath = aClass.toString().replace("class ", "");

        return Class.forName(classPath);
    }


    private void HandleInsertTrigger(Class<?> table) {

        if (!table.isAnnotationPresent(OnInsert.class)) return;

        OnInsert OnInsert = Objects.requireNonNull(table.getDeclaredAnnotation(OnInsert.class));

        if (OnInsert.value().name().isBlank()) return;

        String methodName = OnInsert.value().name();

        InvokeTriggerMethod(methodName);

    }

    private void HandleModifyTrigger(Class<?> table) {

        if (!table.isAnnotationPresent(OnModify.class)) return;

        OnModify onModify = Objects.requireNonNull(table.getDeclaredAnnotation(OnModify.class));

        if (onModify.value().name().isBlank()) return;

        String methodName = onModify.value().name();

        InvokeTriggerMethod(methodName);

    }

    private void HandleDeleteTrigger(Class<?> table) {

        if (!table.isAnnotationPresent(OnDelete.class)) return;

        OnDelete OnDelete = Objects.requireNonNull(table.getDeclaredAnnotation(OnDelete.class));

        if (OnDelete.value().name().isBlank()) return;

        String methodName = OnDelete.value().name();

        InvokeTriggerMethod(methodName);
    }

    private void HandleInitTrigger(Class<?> table) {

        if (!table.isAnnotationPresent(OnInit.class)) return;

        OnInit OnInit = Objects.requireNonNull(table.getDeclaredAnnotation(OnInit.class));

        if (OnInit.value().name().isBlank()) return;

        String methodName = OnInit.value().name();

        InvokeTriggerMethod(methodName);

    }

    private void InvokeTriggerMethod(String methodName) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TableTriggerScan.class);

        Collection<Object> beans = applicationContext.getBeansWithAnnotation(TableTrigger.class).values();

        boolean found = false;

        for (Object bean : beans) {

            Method[] methods = ReflectionUtils.getDeclaredMethods(bean.getClass());

            for (Method method : methods) {

                if (!method.isAnnotationPresent(Trigger.class)) continue;

                if (method.isAnnotationPresent(Trigger.class) && method.getName().equals(methodName)) {
                    ReflectionUtils.invokeMethod(method, bean);
                    found = true;
                    break;
                }
            }

            if (found) break;
        }
    }
}
