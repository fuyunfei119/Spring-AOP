package com.example.aop.Aop;

import com.example.aop.Annotation.*;
import com.example.aop.Config.FieldTriggerScan;
import com.example.aop.Config.TableTriggerScan;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Collection;


@Component
@Aspect
public class TableAOPConfig {

    @Pointcut("execution(* com.example.aop.Record.TestRecord+.Modify(..))")
    public void OnTriggerTableEvent() {
    }

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

    @Around("OnTriggerTableEvent()")
    public Object OnDoTest(ProceedingJoinPoint pjp) throws Throwable {

        Object aClass = pjp.getTarget().getClass().getDeclaredMethod("getaClass").invoke(pjp.getTarget());

        String classPath = aClass.toString().replace("class ", "");

        Class<?> table = Class.forName(classPath);

        System.out.println(table);

        if (table.isAnnotationPresent(OnModify.class)) {
            OnModify declaredAnnotation = table.getDeclaredAnnotation(OnModify.class);
            if (declaredAnnotation != null) {
                if (declaredAnnotation.value() != null) {

                    String methodName = declaredAnnotation.value().name();

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
        }

        return pjp.proceed();
    }
}
